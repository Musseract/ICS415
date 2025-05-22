package com.example;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

public class SimpleBlockWorld {
    private long window;
    private int width = 800, height = 600;
    private float camX, camY, camZ = 3f;
    private float rotX, rotY;
    private double lastMouseX, lastMouseY;
    private boolean firstMouse = true;
    private Map<String, Integer> blocks = new HashMap<>();
    private int blockTexture;
    private GLFWKeyCallback keyCallback;
    private GLFWCursorPosCallback mouseCallback;
    private GLFWMouseButtonCallback mouseBtnCallback;

    public void run() {
        init();
        loadTextures();
        for (int x=-5; x<=5; x++) for (int z=-5; z<=5; z++) placeBlock(x, -1, z);
        loop();
        cleanup();
    }

    private void init() {
        GLFWErrorCallback.createPrint(System.err).set();
        if (!glfwInit()) throw new IllegalStateException("Unable to initialize GLFW");
        glfwDefaultWindowHints();
        window = glfwCreateWindow(width, height, "Simple Block World", NULL, NULL);
        if (window == NULL) throw new RuntimeException("Failed to create window");

        glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_DISABLED);
        keyCallback = new GLFWKeyCallback() {
            public void invoke(long win, int key, int sc, int action, int mods) {
                if (key == GLFW_KEY_ESCAPE && action == GLFW_PRESS) glfwSetWindowShouldClose(win, true);
            }
        };
        glfwSetKeyCallback(window, keyCallback);

        mouseCallback = new GLFWCursorPosCallback() {
            public void invoke(long win, double xpos, double ypos) {
                if (firstMouse) { lastMouseX = xpos; lastMouseY = ypos; firstMouse = false; }
                float dx = (float)(xpos - lastMouseX);
                float dy = (float)(ypos - lastMouseY);
                lastMouseX = xpos; lastMouseY = ypos;
                float sens = 0.1f;
                rotY += dx * sens;
                rotX -= dy * sens;
                rotX = Math.max(-89, Math.min(89, rotX));
            }
        };
        glfwSetCursorPosCallback(window, mouseCallback);

        mouseBtnCallback = new GLFWMouseButtonCallback() {
            public void invoke(long win, int button, int action, int mods) {
                if (action == GLFW_PRESS) {
                    if (button == GLFW_MOUSE_BUTTON_LEFT) modifyBlock(true);
                    if (button == GLFW_MOUSE_BUTTON_RIGHT) modifyBlock(false);
                }
            }
        };
        glfwSetMouseButtonCallback(window, mouseBtnCallback);

        try (MemoryStack stack = stackPush()) {
            IntBuffer pW = stack.mallocInt(1);
            IntBuffer pH = stack.mallocInt(1);
            glfwGetWindowSize(window, pW, pH);
            glfwSetWindowPos(window,
                (glfwGetVideoMode(glfwGetPrimaryMonitor()).width() - pW.get(0)) / 2,
                (glfwGetVideoMode(glfwGetPrimaryMonitor()).height() - pH.get(0)) / 2
            );
        }
        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
        glfwShowWindow(window);
    }

    private void loadTextures() {
        blockTexture = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, blockTexture);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        IntBuffer w = MemoryStack.stackMallocInt(1);
        IntBuffer h = MemoryStack.stackMallocInt(1);
        IntBuffer comp = MemoryStack.stackMallocInt(1);
        ByteBuffer data = STBImage.stbi_load("src/main/resources/block.png", w, h, comp, 4);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, w.get(0), h.get(0), 0, GL_RGBA, GL_UNSIGNED_BYTE, data);
        STBImage.stbi_image_free(data);
    }

    private void loop() {
        GL.createCapabilities();
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_TEXTURE_2D);

        while (!glfwWindowShouldClose(window)) {
            handleMovement();
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            glMatrixMode(GL_PROJECTION);
            glLoadIdentity();
            float aspect = (float) width / height;
            gluPerspective(70f, aspect, 0.1f, 100f);

            glMatrixMode(GL_MODELVIEW);
            glLoadIdentity();
            glRotatef(rotX, 1f, 0f, 0f);
            glRotatef(rotY, 0f, 1f, 0f);
            glTranslatef(-camX, -camY, -camZ);

            glBindTexture(GL_TEXTURE_2D, blockTexture);
            for (String key : blocks.keySet()) {
                String[] parts = key.split(",");  
                renderCube(Float.parseFloat(parts[0]), Float.parseFloat(parts[1]), Float.parseFloat(parts[2]), 1f);
            }

            glfwSwapBuffers(window);
            glfwPollEvents();
        }
    }

    private void handleMovement() {
        float speed = 0.05f;
        if (glfwGetKey(window, GLFW_KEY_W) == GLFW_PRESS) {
            camX += (float)Math.sin(Math.toRadians(rotY)) * speed;
            camZ -= (float)Math.cos(Math.toRadians(rotY)) * speed;
        }
        if (glfwGetKey(window, GLFW_KEY_S) == GLFW_PRESS) {
            camX -= (float)Math.sin(Math.toRadians(rotY)) * speed;
            camZ += (float)Math.cos(Math.toRadians(rotY)) * speed;
        }
        if (glfwGetKey(window, GLFW_KEY_A) == GLFW_PRESS) {
            camX -= (float)Math.cos(Math.toRadians(rotY)) * speed;
            camZ -= (float)Math.sin(Math.toRadians(rotY)) * speed;
        }
        if (glfwGetKey(window, GLFW_KEY_D) == GLFW_PRESS) {
            camX += (float)Math.cos(Math.toRadians(rotY)) * speed;
            camZ += (float)Math.sin(Math.toRadians(rotY)) * speed;
        }
        if (glfwGetKey(window, GLFW_KEY_SPACE) == GLFW_PRESS) camY += speed;
        if (glfwGetKey(window, GLFW_KEY_LEFT_SHIFT) == GLFW_PRESS) camY -= speed;
    }

    private void modifyBlock(boolean destroy) {
        float px = camX, py = camY, pz = camZ;
        float dx = (float)Math.sin(Math.toRadians(rotY));
        float dy = (float)-Math.sin(Math.toRadians(rotX));
        float dz = (float)-Math.cos(Math.toRadians(rotY));
        for (int i = 0; i < 10; i++) {
            px += dx * 0.1f; py += dy * 0.1f; pz += dz * 0.1f;
            int bx = Math.round(px), by = Math.round(py), bz = Math.round(pz);
            String key = bx+"," + by +"," + bz;
            if (destroy && blocks.containsKey(key)) { blocks.remove(key); break; }
            if (!destroy && !blocks.containsKey(key)) { placeBlock(bx,by,bz); break; }
        }
    }

    private void placeBlock(int x, int y, int z) {
        blocks.put(x+","+y+","+z, blockTexture);
    }

    private void renderCube(float x, float y, float z, float size) {
        float hs = size / 2;
        glBegin(GL_QUADS);
        // Cube vertices
        glTexCoord2f(0,0); glVertex3f(x-hs,y-hs,z+hs);
        glTexCoord2f(1,0); glVertex3f(x+hs,y-hs,z+hs);
        glTexCoord2f(1,1); glVertex3f(x+hs,y+hs,z+hs);
        glTexCoord2f(0,1); glVertex3f(x-hs,y+hs,z+hs);
        // ... (other faces) ...
        glEnd();
    }

    private void cleanup() {
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
        glfwTerminate();
        GLFWErrorCallback cb = glfwSetErrorCallback(null);
        if (cb != null) cb.free();
    }

    private void gluPerspective(float fov, float aspect, float zNear, float zFar) {
        float ymax = (float)(zNear * Math.tan(Math.toRadians(fov/2)));
        float ymin = -ymax; float xmin = ymin * aspect; float xmax = ymax * aspect;
        glFrustum(xmin, xmax, ymin, ymax, zNear, zFar);
    }

    public static void main(String[] args) {
        new SimpleBlockWorld().run();
    }
}
