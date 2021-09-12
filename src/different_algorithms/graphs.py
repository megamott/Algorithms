A = [[0, 1, 0, 0, 0, 0, 0, 0, 0, 0],
     [1, 0, 1, 1, 1, 0, 0, 0, 0, 0],
     [0, 1, 0, 0, 0, 0, 0, 0, 1, 0],
     [0, 1, 0, 0, 0, 1, 0, 0, 0, 0],
     [0, 1, 0, 0, 0, 1, 0, 0, 0, 0],
     [0, 0, 0, 1, 1, 0, 1, 0, 0, 0],
     [0, 0, 0, 0, 0, 1, 0, 1, 1, 1],
     [0, 0, 0, 0, 0, 0, 1, 0, 0, 0],
     [0, 0, 1, 0, 0, 0, 1, 0, 0, 1],
     [0, 0, 0, 0, 0, 0, 1, 0, 1, 0]]

A_array = [[j for j in range(len(i)) if i[j] == 1] for i in A]
print(A_array)


def bfs(array_sm: list, start_node: int) -> set:
    visited = {start_node}
    to_explore = [start_node]
    while to_explore:
        current_node = to_explore.pop(0)
        print(current_node)
        new_child_not_visited_nodes = [el for el in array_sm[current_node] if el not in visited]
        to_explore.extend(new_child_not_visited_nodes)
        visited.update(new_child_not_visited_nodes)
    return visited


def dfs(array_sm: list, start_node: int) -> set:
    visited = {start_node}
    to_explore = [start_node]
    while to_explore:
        current_node = to_explore.pop()
        print(current_node)
        new_child_not_visited_nodes = [el for el in array_sm[current_node] if el not in visited]
        to_explore.extend(new_child_not_visited_nodes)
        visited.update(new_child_not_visited_nodes)
    return visited


def find_smallest_way_to_nodes(array_sm: list, start_node: int) -> dict:
    visited = {start_node}
    to_explore = [start_node]
    smallest_ways = {start_node: 0}
    while to_explore:
        current_node = to_explore.pop(0)
        new_child_not_visited_nodes = [el for el in array_sm[current_node] if el not in visited]

        for el in new_child_not_visited_nodes:
            smallest_ways[el] = smallest_ways[current_node] + 1

        to_explore.extend(new_child_not_visited_nodes)
        visited.update(new_child_not_visited_nodes)
    return smallest_ways


# print(bfs(A_array, 0))
# print(dfs(A_array, 0))
# print(find_smallest_way_to_nodes(A_array, 5))
