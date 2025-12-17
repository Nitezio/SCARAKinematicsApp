# 2-DOF SCARA Robot Forward Kinematics Simulation

A JavaFX application that simulates the Forward Kinematics of a 2-Degree-of-Freedom (2-DOF) SCARA robot arm. This project demonstrates the use of **Homogeneous Transformation Matrices (HTM)** to calculate joint positions and end-effector coordinates based on user-defined link lengths and joint angles.

## 📋 Table of Contents
- [Overview](#-overview)
- [Mathematical Logic](#-mathematical-logic)
- [Features](#-features)
- [Prerequisites](#-prerequisites)
- [Installation & Setup Guide](#-installation--setup-guide)
- [Troubleshooting](#-troubleshooting)

## 🔭 Overview
This project was developed for a Robotic System Development course (CSC4702). It visualizes a planar robot arm with two revolute joints. The application takes geometric parameters (link lengths $a_1, a_2$) and joint variables (angles $q_1, q_2$) to compute and draw the robot's pose in real-time.

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

### Calculation Steps implemented in code:
1.  **Base Transformation ($T_1$):** Rotation of Joint 1 ($q_1$) relative to the fixed base.
2.  **Intermediate Transformation ($T_{1 \to 2}$):** Translation along link $a_1$ followed by rotation of Joint 2 ($q_2$).
3.  **Global Transformation ($T_{Global}$):** Computed by matrix multiplication:
    $$T_{Global} = T_1 \cdot T_{1 \to 2}$$
4.  **End-Effector Position:** The final coordinate is found by multiplying the global matrix by the local end-effector vector.

## ✨ Features
* **Dynamic Inputs:** Adjustable lengths ($a_1, a_2$) and angles ($q_1, q_2$).
* **Real-time Visualization:** Draws links, joints, and the end-effector position using JavaFX Canvas.
* **Matrix Implementation:** Custom matrix multiplication logic handles the kinematics chain.
* **Reverse Motion:** Animates the arm returning to its home position (0,0).

## ⚙️ Prerequisites
To avoid version conflicts (like "Unsupported class file major version"), you **must** use a compatible JDK.

* **Java Development Kit (JDK) 21** (LTS Recommended).
    * *Note: Do not use Java 24 or newer, as it is currently incompatible with standard Gradle builds.*
* **IntelliJ IDEA** (Community or Ultimate).

## 🚀 Installation & Setup Guide

### 1. Clone the Repository
Open your terminal or Git Bash and run:
```bash
git clone [https://github.com/YOUR_USERNAME/SCARA-Kinematics.git](https://github.com/YOUR_USERNAME/SCARA-Kinematics.git)
cd SCARA-Kinematics
