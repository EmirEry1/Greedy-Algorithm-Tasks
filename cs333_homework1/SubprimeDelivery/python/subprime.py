import heapq as pq

def subprime_path(capacity_graph, load_graph, start, end):
    print('hello world')

def main():
    c = int(input())
    adjl_capacities = [[] for i in range(c)]
    adjl_loads = [[] for i in range(c)]
    for i in range(c):
        line = input().strip()
        if line.startswith('.'):
            continue
        pairs = line.split(';')
        for p in pairs:
            pair = p.split(',')
            adjl_capacities[i].append((int(pair[0]),int(pair[1])))
    for i in range(c):
        line = input().strip()
        if line.startswith('.'):
            continue
        pairs = line.split(';')
        for p in pairs:
            pair = p.split(',')
            adjl_loads[i].append((int(pair[0]),int(pair[1])))
    start = int(input())
    end=int(input())
    subprime_path(adjl_capacities,adjl_loads,start,end)

main()