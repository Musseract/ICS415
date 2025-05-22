# Simple Block World – README

## Overview

Simple Block World is a basic 3D block-building demo written in Java using LWJGL (Lightweight Java Game Library). It lets you move around a small world, place and remove blocks, and explore basic 3D controls. This project is meant for learning and experimenting with OpenGL, Java, and LWJGL.

---

## Project Structure

- `src/main/java/com/example/SimpleBlockWorld.java` – Main application source code.
- `src/main/resources/block.png` – Texture for the blocks.
- `pom.xml` – Maven build configuration.

---

## How to Build and Run

1. **Build with Maven**

   From the project root, run:
   ```
   mvn package
   ```

2. **Run the JAR**

   ```
   java -jar target/SimpleBlockWorld-1.0-SNAPSHOT-jar-with-dependencies.jar
   ```

   Make sure you have Java (JDK 8 or newer) installed.

---

## Controls

- **W, A, S, D** – Move forward, left, back, and right.
- **Mouse** – Look around.
- **Space** – Move up.
- **Left Shift** – Move down.
- **Left Mouse Click** – Remove a block.
- **Right Mouse Click** – Place a block.
- **ESC** – Exit the application.

---

## Notes

- The `block.png` texture must be present in `src/main/resources/` when building.
- The window starts at 800x600 resolution.
- Requires a graphics card that supports OpenGL.

---

**Enjoy exploring the Simple Block World!**
