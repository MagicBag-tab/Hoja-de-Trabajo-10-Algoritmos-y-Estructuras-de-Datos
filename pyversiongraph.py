#https://networkx.org/documentation/stable/tutorial.html 

import networkx as nx

# Crear un grafo dirigido
G = nx.DiGraph()

# Agregar las estaciones como nodos
stations = {
    "A": "Ciudad de Guatemala",
    "B": "Zacapa",
    "C": "Chiquimula",
    "D": "Quetzaltenango",
    "E": "Cobán"
}

# Agregar las aristas con sus distancias (en km) según la tabla proporcionada
G.add_edge("A", "B", weight=3)  # A -> B: 3 km
G.add_edge("A", "D", weight=7)  # A -> D: 7 km
G.add_edge("B", "C", weight=1)  # B -> C: 1 km
G.add_edge("B", "E", weight=8)  # B -> E: 8 km
G.add_edge("C", "D", weight=2)  # C -> D: 2 km
G.add_edge("D", "E", weight=3)  # D -> E: 3 km
G.add_edge("E", "A", weight=4)  # E -> A: 4 km

# Calcular las distancias más cortas usando el algoritmo de Floyd-Warshall
distances = dict(nx.floyd_warshall(G, weight="weight"))

# Mostrar las conexiones del grafo
print("Conexiones de la red de transporte interdepartamental:")
for u, v, data in G.edges(data=True):
    print(f"{u} -> {v} ({data['weight']} km)")

# Mostrar las distancias más cortas desde Ciudad de Guatemala (A)
print("\nDistancias más cortas desde Ciudad de Guatemala (A):")
for node in stations.keys():
    if node != "A" and distances["A"][node] == float('inf'):
        print(f"A {stations[node]}: No hay ruta")
    elif node != "A":
        print(f"A {stations[node]}: {distances['A'][node]} km")

# Calcular el centro del grafo
eccentricities = nx.eccentricity(G, weight="weight")
min_eccentricity = min(eccentricities.values())
centers = [node for node, ecc in eccentricities.items() if ecc == min_eccentricity]

# Mostrar el centro del grafo
print("\nCentro del grafo:")
for center in centers:
    print(f"{center}: {stations[center]}")
