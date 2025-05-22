# Ray Tracing Project

![image](https://github.com/user-attachments/assets/963e3d5b-8e13-414f-a81d-8b6e3a1dca45)


## Introduction & Motivation

This project is a Python implementation of a simple ray tracer based on the book *Ray Tracing in a Weekend* by Peter Shirley. The goal of this project is to create realistic images by simulating the physics of light as it interacts with objects in a virtual environment.

Ray tracing is a powerful rendering technique used in modern graphics, gaming, and film industries. It allows for realistic lighting, shadows, and reflections. By working on this project, you will gain insight into fundamental computer graphics concepts, including:
- Ray-sphere intersections
- Reflection and refraction
- Diffuse and specular lighting models
- Rendering optimizations

## Installation & Setup

To run this project, you need Python and some essential libraries. Follow the steps below to set up and execute the ray tracer:

### 1. Install Python
Make sure you have Python 3 installed. You can download it from [Python's official website](https://www.python.org/downloads/).

### 2. Install Dependencies
This project requires the following dependencies:
- `pillow` for image processing
- `numpy` for mathematical operations
- `joblib` for parallel processing

To install all required packages, run:
```sh
pip install -r requirements.txt
```

### 3. Run the Ray Tracer
Once the dependencies are installed, execute the main script to generate the ray-traced image:
```sh
python main.py
```
This will process the scene and create an output image (e.g., `output.png`).

## Troubleshooting & Common Errors

### 1. ModuleNotFoundError
**Error:**
```
ModuleNotFoundError: No module named 'pillow'
```
**Solution:**
Run the following command to install missing dependencies:
```sh
pip install pillow numpy joblib
```

### 2. Python Version Issues
**Error:**
```
SyntaxError: invalid syntax
```
**Solution:**
Ensure you are using Python 3 (preferably 3.8 or later). You can check your version by running:
```sh
python --version
```
If you are using an older version, consider upgrading Python.

### 3. Output Image Not Generated
**Error:** No output file appears after execution.
**Solution:** Check the script's print statements to verify that it is running correctly. If there is an issue, try increasing the verbosity of debugging messages by adding print statements inside `main.py` to track progress.

### 4. Performance Issues
Rendering might take a long time, depending on the resolution and number of rays. If the script is slow:
- Reduce the image resolution in `main.py`
- Optimize the number of samples per pixel
- Utilize multiprocessing with `joblib`

