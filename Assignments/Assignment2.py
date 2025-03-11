from PIL import Image
import math

# Image dimensions
WIDTH, HEIGHT = 400, 400
VIEWPORT_SIZE = 1
PROJECTION_PLANE_D = 1
BACKGROUND_COLOR = (255, 255, 255)

# Define spheres
spheres = [
    {"center": (0, -1, 3), "radius": 1, "color": (255, 0, 0), "specular": 500},
    {"center": (2, 0, 4), "radius": 1, "color": (0, 0, 255), "specular": 500},
    {"center": (-2, 0, 4), "radius": 1, "color": (0, 255, 0), "specular": 10},
    # Ground sphere
    {"center": (0, -5001, 0), "radius": 5000, "color": (255, 255, 0), "specular": -1},
]

# Define lights
lights = [
    {"type": "ambient", "intensity": 0.2},
    {"type": "point", "intensity": 0.6, "position": (2, 1, 0)},
    {"type": "directional", "intensity": 0.2, "direction": (-1, -4, -4)},
]

# Helper functions
def dot(v1, v2):
    return sum(v1[i] * v2[i] for i in range(len(v1)))

def subtract(v1, v2):
    return [v1[i] - v2[i] for i in range(len(v1))]

def length(v):
    return math.sqrt(sum(v[i]**2 for i in range(len(v))))

def normalize(v):
    v_length = length(v)
    return [v[i] / v_length for i in range(len(v))]

def multiply(v, scalar):
    return [v[i] * scalar for i in range(len(v))]

# Ray-sphere intersection
def intersect(origin, direction, sphere):
    CO = subtract(origin, sphere["center"])
    a = dot(direction, direction)
    b = 2 * dot(CO, direction)
    c = dot(CO, CO) - sphere["radius"]**2
    disc = b**2 - 4*a*c
    if disc < 0:
        return float("inf")
    t1 = (-b - math.sqrt(disc)) / (2*a)
    t2 = (-b + math.sqrt(disc)) / (2*a)
    return t1 if t1 > 0 else t2

# Compute lighting
def compute_lighting(P, N, V, s):
    intensity = 0.0
    for light in lights:
        if light["type"] == "ambient":
            intensity += light["intensity"]
        else:
            if light["type"] == "point":
                L = subtract(light["position"], P)
            else:  # directional light
                L = light["direction"]

            L = normalize(L)

            # Diffuse
            n_dot_l = dot(N, L)
            if n_dot_l > 0:
                intensity += light["intensity"] * n_dot_l / (length(N) * length(L))

            # Specular
            if s != -1:
                R = subtract(multiply(N, 2 * dot(N, L)), L)
                r_dot_v = dot(R, V)
                if r_dot_v > 0:
                    intensity += light["intensity"] * (r_dot_v / (length(R) * length(V)))**s
    return intensity

# Trace ray
def trace_ray(origin, direction, t_min, t_max):
    closest_t, closest_sphere = float("inf"), None
    for sphere in spheres:
        t = intersect(origin, direction, sphere)
        if t_min < t < t_max and t < closest_t:
            closest_t, closest_sphere = t, sphere

    if closest_sphere is None:
        return BACKGROUND_COLOR

    # Compute intersection point and normal
    P = [origin[i] + closest_t * direction[i] for i in range(3)]
    N = subtract(P, closest_sphere["center"])
    N = normalize(N)
    V = multiply(direction, -1)

    # Compute lighting
    lighting = compute_lighting(P, N, V, closest_sphere["specular"])

    # Apply lighting to sphere color
    sphere_color = closest_sphere["color"]
    return tuple(min(int(c * lighting), 255) for c in sphere_color)

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
        direction = normalize(direction)
        color = trace_ray((0, 0, 0), direction, 1, float("inf"))
        pixels[x, y] = color

# Save and display the image
img.save("output2.png")
img.show()
