import sys
import csv
import numpy as np
import matplotlib.pyplot as plt

allgraph = {}

if len(sys.argv) <= 2:
	dataset = 'natural-disasters-by-type'
else:
	dataset = sys.argv[2]

with open('resources/' + dataset + '.csv') as csvDataFile:
	csvReader = csv.reader(csvDataFile)
	next(csvReader)
	for row in csvReader:
		curr = []
		if allgraph.get(row[0]) == None:
			allgraph[row[0]] = []
			curr = allgraph[row[0]]
		else:
			curr = allgraph.get(row[0])
		curr.append((int(row[2]), int(row[3])))

# Create plot
fig = plt.figure()
ax = fig.add_subplot(1, 1, 1)

if len(sys.argv) >= 2:
	s = allgraph[sys.argv[1]]
	plt.scatter(*zip(*s))
else:
	for cat in allgraph.items():
		print(cat[0])
		print(cat[1])
		s = allgraph[cat[0]]
		plt.scatter(*zip(*s))

if len(sys.argv) >= 2:
	plt.title('Plot of ' + sys.argv[1])
else:
	plt.title('Plot of Natural Disasters')
	plt.legend([(a) for a, b in allgraph.items()])
plt.xlabel('Year')
plt.ylabel('Number of incidents')

print("All credits given to Our World in Data for providing the datasets used in this program.")

plt.show()