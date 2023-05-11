# Spring Boot Kubernetes
[![Build](https://github.com/jdbirla/JD_Spring_Boot_Master/actions/workflows/maven.yml/badge.svg?branch=master)](https://github.com/jdbirla/JD_Spring_Boot_Master/actions/workflows/maven.yml)
- https://www.youtube.com/watch?v=pGbBuwzyiV4&list=PLuNxlOYbv61h66_QlcjCEkVAj6RdeplJJ&index=1
![image](https://user-images.githubusercontent.com/69948118/236795059-beae2678-9332-40be-a667-2985f9d4976b.png)
![image](https://user-images.githubusercontent.com/69948118/236795224-942a664a-6bdc-464e-aa24-bc2345c99ae1.png)
![image](https://user-images.githubusercontent.com/69948118/236795449-a826cbd3-099a-4924-8d9f-de51cb97e2a2.png)
![image](https://user-images.githubusercontent.com/69948118/236795923-997bf120-17cb-4102-9148-5125702c5cd5.png)
![image](https://user-images.githubusercontent.com/69948118/236796165-322c19f6-8740-4fde-9fca-27e2aad43d58.png)
![image](https://user-images.githubusercontent.com/69948118/236798319-2dbeadc0-0f13-4b55-9910-41fc6d356ad2.png)
![image](https://user-images.githubusercontent.com/69948118/236798411-41f51998-7cc6-4c2a-9ad2-fc2db1c51a82.png)
![image](https://user-images.githubusercontent.com/69948118/236800260-b1600046-61ef-49f1-9084-9e59a6ac4b9a.png)
![image](https://user-images.githubusercontent.com/69948118/236992429-1d99b7a2-c47a-469e-8dcd-4749551f2de7.png)
![image](https://user-images.githubusercontent.com/69948118/237020036-b3eee3a1-6ed2-4efd-bc83-dfdc03748540.png)
![image](https://user-images.githubusercontent.com/69948118/237030480-0f99e7e2-868f-4c45-b05f-905cf8e9e492.png)
![image](https://user-images.githubusercontent.com/69948118/237045321-d1183091-689f-4b91-97da-ae184817664a.png)
![image](https://github.com/jdbirla/JD_Spring_Boot_Master/assets/69948118/21fdf605-e4dd-4dd6-80e7-62b55d347fc6)
![image](https://github.com/jdbirla/JD_Spring_Boot_Master/assets/69948118/1e2c6ec3-4582-423f-a5c8-c3f770c63878)
![image](https://github.com/jdbirla/JD_Spring_Boot_Master/assets/69948118/5da94c16-88bd-4415-bb57-cb69bdbf2330)
![image](https://github.com/jdbirla/JD_Spring_Boot_Master/assets/69948118/4906001b-1417-4f91-ae90-bdc138801981)
![image](https://github.com/jdbirla/JD_Spring_Boot_Master/assets/69948118/7c550a23-cf12-4e56-85e0-426b724e8ed8)
![image](https://github.com/jdbirla/JD_Spring_Boot_Master/assets/69948118/505cf626-d73d-4a7c-ba02-4bacb2cb561d)
![image](https://github.com/jdbirla/JD_Spring_Boot_Master/assets/69948118/fc8f490c-d8fe-465e-996d-573ebf998b50)
![image](https://github.com/jdbirla/JD_Spring_Boot_Master/assets/69948118/725a98fb-7abd-4f25-898d-ba010c132640)
![image](https://github.com/jdbirla/JD_Spring_Boot_Master/assets/69948118/e8d59f1e-7212-47ac-afc7-7d2895021a84)
![image](https://github.com/jdbirla/JD_Spring_Boot_Master/assets/69948118/17681347-0944-4933-a5e6-4e6b11a36cb3)
![image](https://github.com/jdbirla/JD_Spring_Boot_Master/assets/69948118/77125ccc-a690-4264-9c5d-74cbccabd190)
![image](https://github.com/jdbirla/JD_Spring_Boot_Master/assets/69948118/d1444baf-9f57-4a77-8ee5-379efcdbcaa2)
![image](https://github.com/jdbirla/JD_Spring_Boot_Master/assets/69948118/e0ef1ce8-5c7a-4a5d-b606-d4658c8f3b39)
### Install Kind
- To install KinD on Windows 10, you can follow these steps:

- Install Docker Desktop for Windows by downloading it from the Docker website.

- After installation, launch Docker Desktop and go to "Settings" followed by "Resources" and then "WSL Integration."

- Make sure that "Enable integration with my default WSL distro" is checked, and select the WSL distribution you have.

- Open PowerShell as an administrator.

- Install Chocolatey package manager by running the following command:
```
Set-ExecutionPolicy Bypass -Scope Process -Force; [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072; iex ((New-Object System.Net.WebClient).DownloadString('https://chocolatey.org/install.ps1'))
```
-  Install KinD using the following command:
``` choco install kind ```
- To confirm the installation, run the command `kind version` in PowerShell.
![image](https://github.com/jdbirla/JD_Spring_Boot_Master/assets/69948118/02888ba0-6287-4e44-8bbb-693b0a3cc82b)
### Install k8s Lens
- k8sLens desktop exe and install it

