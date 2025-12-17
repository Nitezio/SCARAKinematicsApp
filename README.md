# 2-DOF SCARA Robot Forward Kinematics Simulation

A JavaFX application that simulates the Forward Kinematics of a 2-Degree-of-Freedom (2-DOF) SCARA robot arm. This project demonstrates the use of **Homogeneous Transformation Matrices (HTM)** to calculate joint positions and end-effector coordinates based on user-defined link lengths and joint angles.

---

## 📋 Table of Contents
- [Overview](#-overview)
- [Mathematical Logic](#-mathematical-logic)
- [Features](#-features)
- [Prerequisites](#-prerequisites)
- [Installation & Setup Guide](#-installation--setup-guide)
- [Troubleshooting](#-troubleshooting)
- [Contributing](#-contributing)

---

## 🔭 Overview
This project was developed for a **Robotic System Development course (CSC4702)** at the University level. It provides an interactive visualization of a planar robot arm with two revolute joints operating in 2D space.

The application demonstrates fundamental concepts in robotics:
- **Forward Kinematics:** Computing end-effector position from joint angles
- **Homogeneous Transformation Matrices:** Mathematical framework for coordinate transformations
- **Real-time Simulation:** Dynamic visualization of robot configurations

Users can input geometric parameters (link lengths $a_1, a_2$) and joint variables (angles $q_1, q_2$) to see the robot's pose updated in real-time on a graphical canvas.

---

## 🧮 Mathematical Logic
The core of this simulation relies on **Forward Kinematics** using Homogeneous Transformation Matrices.

Given a frame $\{A\}$ and a frame $\{B\}$, the position of a point $P$ can be transformed using:

$$P^A = T_B^A \cdot P^B$$

Where the transformation matrix $T$ for a planar rotation $\theta$ and translation $(t_x, t_y)$ is:

$$
T = \begin{pmatrix} 
\cos\theta & -\sin\theta & t_x \\
\sin\theta & \cos\theta & t_y \\
0 & 0 & 1 
\end{pmatrix}
$$

### Calculation Steps Implemented in Code:
1. **Base Transformation ($T_1$):** Rotation of Joint 1 ($q_1$) relative to the fixed base frame.
2. **Intermediate Transformation ($T_{1 \to 2}$):** Translation along link $a_1$ followed by rotation of Joint 2 ($q_2$).
3. **Global Transformation ($T_{Global}$):** Computed by matrix multiplication:
   $$T_{Global} = T_1 \cdot T_{1 \to 2}$$
4. **End-Effector Position:** The final coordinate is found by multiplying the global transformation matrix by the local end-effector vector $[a_2, 0, 1]^T$.

---

## ✨ Features
* 🎛️ **Dynamic Inputs:** Adjustable link lengths ($a_1, a_2$) and joint angles ($q_1, q_2$) via text fields
* 🎨 **Real-time Visualization:** Interactive drawing of links, joints, and end-effector position using JavaFX Canvas
* 🧮 **Matrix Implementation:** Custom matrix multiplication logic handles the transformation chain
* 🔄 **Reverse Motion Animation:** Smooth animation showing the arm returning to its home position (0°, 0°)
* 📐 **Coordinate Display:** Shows calculated X and Y coordinates of the end-effector
* 🖱️ **User-friendly Interface:** Clean GUI with labeled inputs and visual feedback

---

## ⚙️ Prerequisites
To avoid version conflicts (like "Unsupported class file major version"), you **must** use a compatible JDK.

* **Java Development Kit (JDK) 21** (LTS Recommended)
    > ⚠️ **Warning:** Do not use Java 24 or newer, as it is currently incompatible with standard Gradle builds and may cause "version 68" class file errors.
* **IntelliJ IDEA** (Community or Ultimate Edition)
* **Gradle** (Bundled with the project, no separate installation needed)

---

## 🚀 Installation & Setup Guide

### 1. Clone the Repository
Open your terminal or Git Bash and run:
```bash
git clone https://github.com/Nitezio/SCARAKinematicsApp.git
cd SCARAKinematicsApp
```

### 2. Open in IntelliJ IDEA
1. Open **IntelliJ IDEA**.
2. Select **File > Open** and navigate to the `SCARAKinematicsApp` folder.
3. **Important:** When prompted, choose **"Load Gradle Project"** or **"Trust Project"**.

### 3. CRITICAL: Configure Gradle JVM
> **Most build errors happen here. Follow this step carefully to prevent build failures.**

1. In IntelliJ, go to **Settings** (Windows: `Ctrl+Alt+S`, Mac: `Cmd+,`).
2. Navigate to **Build, Execution, Deployment > Build Tools > Gradle**.
3. Look for **Gradle JVM** at the bottom of the settings panel.
4. **Ensure it is set to Java 21.**
    * *If it says "Java 24" or isn't available:* 
      - Click the dropdown menu
      - Select **Download JDK...**
      - Choose **Version 21** (any vendor: Oracle OpenJDK, Amazon Corretto, etc.)
      - Click **Download**
5. Click **Apply** and **OK**.

### 4. Sync Gradle Dependencies
1. Look for the **Gradle** tab on the right sidebar of IntelliJ.
2. Click the **Reload All Gradle Projects** icon (🔄 elephant with circular arrows).
3. Wait for the sync to complete (status bar at the bottom will show progress).

### 5. Run the Application
1. In the Project Explorer, navigate to: `src/main/java/org/example/scarakinematicsapp/`
2. **Right-click on `Launcher.java`** (NOT `SCARAKinematicsApp.java`)
3. Select **Run 'Launcher.main()'**

> 💡 **Why Launcher?** JavaFX requires a module workaround. Running the App class directly will cause a "Runtime components missing" error. The Launcher class bypasses this requirement by using a non-Application entry point.

---

## 🔧 Troubleshooting

| Error Message | Solution |
|:-------------|:---------|
| **"JavaFX runtime components are missing"** | You are likely running `SCARAKinematicsApp.java`. Stop and run **`Launcher.java`** instead. |
| **"Unsupported class file major version 68"** | You are using Java 24. Go to **Settings > Build Tools > Gradle** and change **Gradle JVM** to **Java 21**. Then reload the Gradle project. |
| **"Reference to undefined variable"** | Click the **Reload All Gradle Projects** icon (🔄) in the Gradle sidebar to re-sync dependencies. |
| **"Module not found: javafx.controls"** | Gradle dependencies not loaded. Go to **File > Invalidate Caches** > Check "Clear file system cache and Local History" > **Invalidate and Restart**. |
| **Blank window or no drawing appears** | Check the console for errors. Ensure all input fields have valid numeric values. Try resizing the window to trigger a redraw. |

### Still Having Issues?
1. **Clean and rebuild:** Go to **Build > Clean Project**, then **Build > Rebuild Project**
2. **Delete build cache:** Close IntelliJ, delete the `.gradle` and `build` folders in your project directory, then reopen and sync
3. **Verify Java version:** Run `java -version` in terminal to confirm JDK 21 is installed system-wide

---

## 🤝 Contributing

Contributions are welcome! If you'd like to contribute to this project:

### How to Contribute:
1. **Fork this repository** by clicking the "Fork" button at the top right
2. **Clone your fork** locally:
   ```bash
   git clone https://github.com/YOUR_USERNAME/SCARAKinematicsApp.git
   cd SCARAKinematicsApp
   ```
3. **Create a new branch** for your feature:
   ```bash
   git checkout -b feature-name
   ```
4. **Make your changes** and commit them with descriptive messages:
   ```bash
   git add .
   git commit -m 'Add feature: description of your changes'
   ```
5. **Push to your fork**:
   ```bash
   git push origin feature-name
   ```
6. **Open a Pull Request** on the original repository:
   - Go to the original repo on GitHub
   - Click "Pull Requests" > "New Pull Request"
   - Select your fork and branch
   - Describe your changes and submit

### Contribution Guidelines:
- ✅ Write clear, commented code
- ✅ Follow existing code style and structure
- ✅ Test your changes before submitting
- ✅ Update documentation if you add new features
- ✅ Be respectful and constructive in discussions

---

## 🙏 Acknowledgments

- Developed as part of **CSC4702: Robotic System Development** coursework
- Special thanks to the course instructor and teaching assistants
- Built with **JavaFX** for modern GUI development
- Mathematical foundations based on standard robotics textbooks (Craig, Spong, etc.)

---

## 📧 Contact

If you have questions, suggestions, or find any issues:
- Open an [Issue](https://github.com/Nitezio/SCARAKinematicsApp/issues) on GitHub
- Reach out to the repository owner: [@Nitezio](https://github.com/Nitezio)

---

<div align="center">

**If you found this project helpful, consider giving it a ⭐ star!**

Made with ❤️ for robotics education

</div>
