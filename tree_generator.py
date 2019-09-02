import json

tree_dict = {
    1: {
        -1: {None: {}},
        10: {None: {}},
    },
    15: {None: {}}
}

counter = 0

def dict_to_java_tree(root_value, root_children, type_param, 
        binary=True):
    global counter
    tree_str = ('BinaryTree' if binary else 'StandardTree') + f'<{type_param}>'
    out = []
    if root_value is None:
        root_value = 'null'
    else:
        root_value = repr(root_value)
    this_counter = counter
    out.append(f'{tree_str} tree{counter} = new {tree_str}({root_value});')
    counter += 1
    for i, (c_value, c_children) in enumerate(root_children.items()):
        if i >= 2 and binary: 
            raise ValueError("More than two children in binary tree.")
        child_counter = counter
        out.extend(dict_to_java_tree(c_value, c_children, type_param, True))
        out.append(f'tree{this_counter}.addChild(tree{child_counter});')
    return out
        




if __name__ == "__main__":
    print('\n'.join(dict_to_java_tree(2, tree_dict, 'Integer')))