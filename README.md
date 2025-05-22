# ICS415 Course Projects

This repository collects major projects and assignments for the ICS415 course, covering a range of computer graphics, game programming, and interactive applications. Each project is self-contained in its own folder.

---

## 📦 Folder Structure

```
RayTracingProject/         # Project 01 – Python Ray Tracer
ShaderToyUnityProject/     # Project 02 – ShaderToy GLSL in Unity
BlockWorld/                # Project 03 – Java LWJGL Block World
BezierCurveEditor/         # Project 04 – Bézier Curve Web Editor
Assignments/               # Homework and lab submissions
```

---

## 🚀 Projects Overview

### **Project 01 – Ray Tracing in Python**

A simple but powerful ray tracer built in Python, inspired by "Ray Tracing in a Weekend."

* **Features:** Realistic images using ray-sphere intersection, reflections, and lighting models.
* **Run:**

  1. Install Python 3 and dependencies (`pip install -r requirements.txt`)
  2. Run with `python main.py`
* **Output:** Produces a PNG image of a ray-traced scene.
* **Learn:** Modern rendering, physical lighting, and computer graphics basics.
  *(See RayTracingProject/README.md for details)*

---

### **Project 02 – ShaderToy GLSL Shader in Unity**

A Unity project that converts and runs a specific ShaderToy (GLSL) shader in a Unity scene.

* **Features:**

  * Converted ShaderToy shader (GLSL → HLSL/ShaderLab for Unity)
  * Fully set up Unity scene and materials
* **Run:**

  1. Open the project folder in Unity Hub
  2. Open the sample scene and press Play
* **Learn:** Shader conversion, visual effects, and Unity integration.
* **No coding required:** Just open and play!
  *(See ShaderToyUnityProject/README.md for details)*

---

### **Project 03 – Simple Block World (Java + LWJGL)**

A 3D block-building mini-game in Java using LWJGL (like a mini Minecraft).

* **Features:**

  * Move around in 3D, place/remove blocks, explore a block world
  * WASD, mouse controls, block textures, simple OpenGL setup
* **Run:**

  1. Build with Maven (`mvn package`)
  2. Run the JAR (`java -jar target/SimpleBlockWorld-1.0-SNAPSHOT-jar-with-dependencies.jar`)
* **Learn:** Game programming, OpenGL, and real-time graphics in Java.
  *(See BlockWorld/README.md for details)*

---

### **Project 04 – Bézier Curve Web Editor**

A browser-based editor for learning and playing with Bézier curves.

* **Features:**

  * Draw, drag, and edit cubic/poly Bézier curves on an interactive canvas
  * Single-file HTML, no dependencies, works in any modern browser
* **Run:** Just open `index.html` in your browser.
* **Learn:** Interactive graphics, geometric modeling, and web UI design.
  *(See BezierCurveEditor/README.md for details)*

---

## 🔖 Assignments

All homework, labs, and smaller exercises are organized in the `Assignments/` folder.

---

## 🛠️ Getting Started

1. **Clone this repo:**

   ```bash
   git clone https://github.com/<your-username>/ICS415.git
   ```
2. **Open the folder for the project you want.**
3. **See the individual README in each project folder for full details and setup.**

---

## 📝 Notes

* For best results, use the software versions recommended in each project.
* For help, check the specific README or contact the repo owner.
* All projects are for educational and academic use.

---
