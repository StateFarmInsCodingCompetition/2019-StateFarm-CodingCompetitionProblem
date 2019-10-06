library(plotly)
p <- plot_ly(midwest, x = ~percollege, color = ~state, type = "box")
p
