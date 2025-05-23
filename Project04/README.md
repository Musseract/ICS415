# Bézier Curve Editor – README

## Overview

The Bézier Curve Editor is an interactive web application for visualizing and editing Bézier curves. Designed for intuitive learning and experimentation, it allows users to construct and manipulate both single and multiple-segment Bézier curves directly in the browser. This tool is suitable for students, educators, and anyone interested in computer graphics or geometric modeling.

---

## Features

- **Two Curve Modes**:
  - **Basic Bézier Mode**: Work with a single cubic Bézier curve (four points).
  - **Poly Bézier Mode**: Create and edit complex curves by adding or removing multiple cubic Bézier segments.

- **Intuitive Canvas Interaction**:
  - Click to add or select points.
  - Drag points to adjust curve shapes in real time.
  - Remove the last point or segment using the “Remove Last” button or the **R** keyboard shortcut.

- **Clear Visual Cues**:
  - **Red points** for anchor nodes, **green points** for control nodes.
  - Control lines and curve paths are clearly distinguished for ease of use.

- **Responsive and Accessible UI**:
  - Canvas auto-resizes with the window.
  - High-contrast toolbar and easy-to-read instructions.
  - Keyboard and mouse support for primary actions.

- **On-Page Instructions**:
  - Always-visible footer with clear, concise usage tips.

---

## How to Use

1. **Open `index.html` in any modern web browser.**
   - No installation or setup required.

2. **Select a Curve Mode**:
   - **Basic Bézier Mode**: Use for editing a single cubic curve. Default mode on load.
   - **Poly Bézier Mode**: Click to enable adding/removing segments.

3. **Adding and Manipulating Points**:
   - Click on the canvas to add points (in Poly mode when adding segments).
   - Drag existing points to reshape the curve.

4. **Editing Segments**:
   - In Poly Bézier Mode, click “Add Segment” and then click on the canvas to extend the curve.
   - Use “Remove Last” to delete the most recently added segment.

5. **Shortcuts and Guidance**:
   - Press **R** to remove the last point or segment.
   - Read the instructions in the footer at any time for quick help.

---

## Project Structure

- **index.html**: Contains all code (HTML, CSS, JS).  
  - `<div id="toolbar">` – Toolbar with mode and segment controls.
  - `<canvas id="canvas">` – Main drawing area.
  - `<footer>` – Usage instructions.
  - `<script>` – Handles rendering and user interaction logic.

---

## Accessibility & Inclusivity

- Color-coded points and high-contrast UI for visibility.
- Keyboard shortcuts for essential actions.
- Fully responsive design suitable for desktops and tablets.
- Minimalist layout for distraction-free use.
- Clear, simple language in all instructions.

---

## Customization and Extensibility

- All core logic and styling are in `index.html` for easy modification.
- Add more features, such as export functions or support for higher-degree Bézier curves, by extending the JavaScript section.
- Adjust styles in the `<style>` block as needed.

---

## Troubleshooting

- **If the canvas doesn’t show or work correctly:**  
  Ensure you are using a modern browser and that JavaScript is enabled.
- **If buttons or drag actions do not work:**  
  Reload the page and try again; resizing the window may help.
- **For accessibility adjustments:**  
  Colors and font sizes can be changed in the `<style>` section.

---

## License

This project is intended for educational and academic use.  
No third-party libraries or frameworks are used.

---

**Enjoy exploring and learning with the Bézier Curve Editor!**
