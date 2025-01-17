from PIL import Image

# Image dimensions
WIDTH, HEIGHT = 400, 400
VIEWPORT_SIZE = 1
PROJECTION_PLANE_D = 1
BACKGROUND_COLOR = (255, 255, 255)

# Define spheres
spheres = [
    {"center": (0, -1, 3), "radius": 1, "color": (255, 0, 0)},
    {"center": (2, 0, 4), "radius": 1, "color": (0, 0, 255)},
    {"center": (-2, 0, 4), "radius": 1, "color": (0, 255, 0)},
]

# Ray-sphere intersection
def intersect(origin, direction, sphere):
    CO = [origin[i] - sphere["center"][i] for i in range(3)]
    a = sum(d**2 for d in direction)
    b = 2 * sum(CO[i] * direction[i] for i in range(3))
    c = sum(CO[i]**2 for i in range(3)) - sphere["radius"]**2
    disc = b**2 - 4*a*c
    return (-b - disc**0.5) / (2*a) if disc >= 0 else float('inf')

# Generate image
img = Image.new("RGB", (WIDTH, HEIGHT), BACKGROUND_COLOR)
pixels = img.load()
for y in range(HEIGHT):
    for x in range(WIDTH):
        direction = [
            (x - WIDTH / 2) * VIEWPORT_SIZE / WIDTH,
            -(y - HEIGHT / 2) * VIEWPORT_SIZE / HEIGHT,
            PROJECTION_PLANE_D,
        ]
        magnitude = sum(d**2 for d in direction)**0.5
        direction = [d / magnitude for d in direction]

        closest_t, color = float('inf'), BACKGROUND_COLOR
        for sphere in spheres:
            t = intersect((0, 0, 0), direction, sphere)
            if 1 < t < closest_t:
                closest_t, color = t, sphere["color"]
        pixels[x, y] = color

# Save and display the image
img.save("output.png")
img.show()
