import sys
import csv
import numpy as np
import matplotlib.pyplot as plt

allgraph = {}

if len(sys.argv) == 2:
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
			curr = []
		else:
			curr = allgraph.get(row[0])
		curr.append((int(row[2]), int(row[3])))

# Create plot
fig = plt.figure()
ax = fig.add_subplot(1, 1, 1)

s = allgraph[sys.argv[1]]

plt.scatter(*zip(*s))

plt.title('Plot of ' + sys.argv[1])
plt.xlabel('Year')
plt.ylabel('Number of incidents')
plt.show()