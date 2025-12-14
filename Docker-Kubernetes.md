# Welcome to Docker and Kubernetes 101

Here I will document about **Docker and Kubernetes** for future reference.

<p>

<img src="https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white" alt="Docker">
<img src="https://img.shields.io/badge/kubernetes-%23326ce5.svg?style=for-the-badge&logo=kubernetes&logoColor=white" alt="Kubernetes">

</p>

We have learnt about development for the last couple of years in various technologies, frameworks and languages like Python, Golang, Typescript, Java etc, but we never realised to deploy the service/tool/anything we developed for people to access it. Sure we may have used modern tools like vercel or render, but being a Software Developer we should know how to customize the flow and understand how deployment actually works. We cannot rely on vercel or render for enterprise code.

<img src="https://i.redd.it/cg5q7x6wt5k71.jpg" alt="deployment meme, why is it important" width="500px">

In SDLC, we can see that deployment is essential ofcourse, and we note that the process of deployment is a recurrent phase, hence it should be Optimised and Time-efficent, right ?

<img src="https://d12zh9bqbty5wp.cloudfront.net/images/f9e66e179b6747ae54108f82f8ade8b3c25d76fd30afde6c395822c530196169-1676404980185.png" alt="SDLC" width="500px">

---

## Traditional Deployment - Old School but Time Consuming 

> ### What makes a server, well a Server ?
> In short, any computer with constant internet access, should be running all the time (to serve something) and should have a Static IP address (like a permanent address, for reliablity) to it, that's it!

In the traditional approach, we work and deploy on physical hardware aka servers manually, we can lend servers from organizations and then we can deploy the whole system there. Ofc there is a catch here, the software is difficult to maintain and scale, cannot balance loads, and everything has to be configured according to the server env & OS. 
Everyone have to have the same configuration and dependencies, otherwise "It works on my machine only"

Imagine a person developing a service on windows and then has to deploy it on linux, the dependencies has to be the same, bad DX  :(

---

## Virtualization Deployment - Almost there

> ### What is a hypervisor?
> A hypervisor is a software that you can use to run multiple virtual machines on a single physical machine. Every virtual machine has its own operating system and applications. The hypervisor allocates the underlying physical computing resources such as CPU and memory to individual virtual machines as required. Thus, it supports the optimal use of physical IT infrastructure.
>
> <i> - [AWS website](https://aws.amazon.com/what-is/hypervisor/)</i>

> ### What is a Virtual Machine?
> A virtual machine (VM) is a digital version of a physical machine that functions as an isolated system with its own virtualized hardware resources and operating system. A physical machine, like a laptop or physical server, typically has a single operating system coordinating the hardware to run your applications. However, underlying physical resources can provide significantly more power than a single system can use.
>
> <i> - [AWS website](https://aws.amazon.com/what-is/virtual-machine/)</i>

Thankfully, now we can work on Virtual Machines and with the introduction of remote cloud provider, we can create VMs and run our code/service on that right ? ... right ?

Well, VMs are awesome, but do come with challenges, they are heavy, large, slow to scale and are costly to manage & maintain.

## Containerization 🎉
Containerization is packaging a service/application (code, dep, lib, config) into a single unit called a **container** that runs on a container engine, making it run consistently anywhere, from a developer's laptop to the cloud. It finally truely solves the _"It works on my machine problem"_ . We create images and container which makes the application modular, portable, cost efficient and scalable. Easy to version control, and helps in **DevOps**.
// TODO

## <i>"It works on my machine"</i>

<img src="https://external-preview.redd.it/kPQ__at4fKKgnYwxUE1Y50bSW7dMyypwpCgPUmFRg04.jpg?auto=webp&s=f3b7d41ce149fcc8074a02bd90f6d82f59c9544b" alt="it works on my machine meme" width="500px">

### VM vs Container

| Feature | Virtual Machine (VM) | Container |
|---------|----------------------|-----------|
| Virtualizes | Hardware | Operating System |
| OS Required | Yes, full Guest OS per VM | No separate OS |
| Kernel | Own kernel | Shares host kernel |
| Size | Large (GBs) | Small (MBs) |
| Startup Time | Slow (minutes) | Fast (seconds) |
| Resource Usage | High CPU and RAM | Low and efficient |
| Isolation | Strong | Moderate |
| Performance | Slower due to OS overhead | Near native |
| Portability | Harder to move | Easy to move |
| Scalability | Slow to scale | Fast to scale |
| Use Case | Legacy apps, strong isolation | Microservices, APIs |
| Deployment | VM images | Docker images |
| Cost | Higher infra cost | Lower infra cost |
| Example Tools | VirtualBox, VMware, EC2 | Docker, Podman |
---

<img src="https://s7280.pcdn.co/wp-content/uploads/2018/07/containers-vs-virtual-machines.jpg" alt="VM vs container" width="500px">

<img src="https://preview.redd.it/16q5ebaotly71.png?auto=webp&s=cff5de40626ccd34c8f58e73016a1eadddea076d" alt="VM vs container meme" width="500px">

<img src="https://media.licdn.com/dms/image/v2/D5612AQEN3VKUit15vQ/article-cover_image-shrink_720_1280/article-cover_image-shrink_720_1280/0/1715412904464?e=2147483647&v=beta&t=wZY8fRlfcpzPwmpwg6AXyaCcYUZTKYF9lxBYtaP1xZA" alt="td vs vd vs cd" width="500px">

---

<img src="https://raw.githubusercontent.com/marwin1991/profile-technology-icons/refs/heads/main/icons/docker.png" width="100px">


## What is Docker 🐋 ?
Docker is a tool that helps you package your application along with everything it needs  
(libraries, dependencies, runtime) into a single unit called an **image**.

That image can be run anywhere using a **container**.

Simple idea:
- Image = blueprint
- Container = running app

---

<img src="https://i.ytimg.com/vi/Cs2j-Rjqg94/maxresdefault.jpg" alt="what is docker" width="500px">

### Docker Concepts
Lets simulate how one would use docker in their project.

- We can use `docker init` to initialize the docker config files.

We start with a **Dockerfile**, in our project directory, it basically tells docker how to build the image.

Example: A basic python script

```Dockerfile
# base image
FROM python:3.11-slim
# we would have to create a working dir
WORKDIR /app
# copy the code n logic {COPY from to}
COPY . .
RUN pip install -r requirements.txt
CMD ["python", "app.py"]
```

Another example: A typescript project

```Dockerfile
# Stage 1: Builder
FROM node:18 AS builder

WORKDIR /app

COPY package*.json ./

RUN npm install

COPY . .

RUN npm run build


# Stage 2: Final (lighter)
FROM node:18-slim AS final

WORKDIR /app

# Copy only what is needed for production
COPY --from=builder /app/package*.json ./
COPY --from=builder /app/node_modules ./node_modules
COPY --from=builder /app/dist ./dist


EXPOSE 8080

CMD ["npm", "run", "start"]
```

### Basic Docker Commands

- Use `docker --version` to check Docker installation
- Use `docker info` to see Docker system information
- Use `docker login` to log in to Docker Hub
- Use `docker logout` to log out from Docker Hub

### Container Commands

- Use `docker ps` to check all running containers
- Use `docker ps -a` to check all containers (running + stopped)
- Use `docker run IMAGE` to create and start a container
- Use `docker run -d IMAGE` to run container in detached mode
- Use `docker run -p 8080:80 IMAGE` to map ports
- Use `docker run --name my_container IMAGE` to name a container
- Use `docker stop CONTAINER_ID` to stop a running container
- Use `docker start CONTAINER_ID` to start a stopped container
- Use `docker restart CONTAINER_ID` to restart a container
- Use `docker rm CONTAINER_ID` to delete a container
- Use `docker logs CONTAINER_ID` to view container logs
- Use `docker exec -it CONTAINER_ID bash` to enter a container

### Image Commands

- Use `docker images` to list all images
- Use `docker pull IMAGE` to download an image
- Use `docker build -t IMAGE_NAME .` to build an image
- Use `docker rmi IMAGE_ID` to delete an image
- Use `docker tag IMAGE USERNAME/IMAGE` to tag image for Docker Hub
- Use `docker push USERNAME/IMAGE` to push image to Docker Hub

### Volume Commands

- Use `docker volume ls` to list volumes
- Use `docker volume create VOLUME_NAME` to create a volume
- Use `docker run -v VOLUME_NAME:/path IMAGE` to mount volume
- Use `docker volume rm VOLUME_NAME` to delete a volume

### Network Commands

- Use `docker network ls` to list networks
- Use `docker network create NETWORK_NAME` to create a network
- Use `docker network rm NETWORK_NAME` to delete a network

### Cleanup Commands

- Use `docker system df` to check Docker disk usage
- Use `docker system prune` to remove unused data
- Use `docker container prune` to remove stopped containers
- Use `docker image prune` to remove unused images

> ## NOTE: Layer Caching
> Dockerfiles are always prefered to be written in a specific order as the order of all the stages matter. If a layer doesn't change when building the same images, it caches unchanged layers **_docker is sihmart 😆_**

**Did you ponder, why do most tutorials go for making APIs with port forwarding and all, well docker is not just limited to making APIs ...**

### What Else Can Be Dockerized?
- **Worker / Consumer**: A long-running background process that reads a queue or stream.  
    - **Examples**: Email sender, image processor, payment reconciler.

- **Cron / Scheduled Job**: Runs on a schedule and exits.  
    - **Examples**: Nightly report, database cleanup, backup.

- **Batch / One-Time Job**: Runs once to completion and saves output.  
    - **Examples**: Data migration, database seeding, ETL job.

- **CLI / Utility Tool**: A command-line tool you run in CI or ad hoc.  
    - **Examples**: Database migration, report generation.

- **Model Trainer / Inference Runner**: Machine learning training or batch inference, which can be long-running or scheduled.

- **Sidecar Container**: A helper container co-located with the main container in the same pod.  
    - **Examples**: Log forwarder, proxy, configuration updater.

- **Init Container**: Runs before the main container to set up the state.  
    - **Examples**: Downloading secrets, migrating schemas.

- **Stateful Services That Are Not HTTP APIs**: Services like databases, caches, or message brokers with their own ports and storage.

---

### How These Modules Are Accessed or Used in Production

Not all modules are "accessed" the same way as an HTTP API. Below are the main communication patterns:
- **Message Queue or Pub/Sub**  
    - Producers push messages, and consumers (containers) read from the queue.  
    - Tools: RabbitMQ, Kafka, SQS, Pub/Sub.  
    - Access: The worker connects to the queue endpoint and processes messages.

- **Database / Storage Trigger**  
    - A writer writes a row, and a worker polls the database or listens to a change stream.  
    - Access: The worker reads the database, processes data, and writes results or files.

- **Files and Object Storage**  
    - A file is dropped in S3 or a shared volume, and a worker reads and processes it.  
    - Access: The worker has credentials or a mount to read the file.

- **RPC or CLI Invocation**  
    - Another service calls the container via RPC or runs a job using `docker run` or through CI.  
    - Access: Triggered by another service or CI job.

- **Cron / Scheduler**  
    - A scheduler starts a container on a time schedule.  
    - Access: Triggered by schedule only.

- **Sidecar Patterns and Shared Volumes**  
    - Containers inside the same pod share a filesystem or localhost ports.  
    - Access: The sidecar communicates with the main container via localhost.

- **Logs and Metrics**  
    - Results are accessed via logs, metrics endpoints, or by reading outputs uploaded to storage.  
    - Access: Use `docker logs`, `kubectl logs`, or a centralized logging system.


---


### Container Registry

A container registry is a storage system (like a library) for container images (blueprints for apps), allowing developers to store, share, and manage them efficiently, essential for DevOps and CI/CD, with options like public (Docker Hub) or private (Azure ACR, AWS ECR, GCP GCR), offering features like security, versioning, and integration with cloud platforms. 

<p style="display:flex; gap:20px">
<img src="https://raw.githubusercontent.com/marwin1991/profile-technology-icons/refs/heads/main/icons/aws.png" width="30px">
<img src="https://raw.githubusercontent.com/marwin1991/profile-technology-icons/refs/heads/main/icons/gcp.png" width="30px">
<img src="https://raw.githubusercontent.com/marwin1991/profile-technology-icons/refs/heads/main/icons/microsoft_azure.png" width="30px">
<img src="https://raw.githubusercontent.com/marwin1991/profile-technology-icons/refs/heads/main/icons/digital_ocean.png" width="30px">
</p>
<br>

<img src="https://miro.medium.com/v2/resize:fit:1400/1*STd9u5TPBJx1PtIr5C2ASA.jpeg" alt="containers don't solve evrything" width="500px">

### Disadvantages of Docker 🙆
1. **Uses more system resources:** Containers share the host OS, but running many containers still loads the CPU and RAM heavily.
If the app is badly optimized, containers make it even slower.

2. **Not ideal for apps needing a full OS:** If your software expects a complete operating system or needs system level access, Docker might not support it well.

3. **Complex networking:** Docker networking is easy at first, but when you deal with multiple containers, custom networks, volume mounts, and port conflicts, it gets confusing.

4. **Storage and volume issues:** Persistent data handling is not always simple. Volumes can break or get corrupted if used incorrectly.

5. **Not great for GUI apps:** Docker is built for server apps. Running desktop or graphical programs inside a container is tricky.

6. **Security risks if misconfigured:** Containers share the kernel with the host.
If one container escapes or is misconfigured, it can affect the host system.


---
### Docker Compose 🎉🐋

Docker Compose is a tool for defining and running multi-container applications. It is the key to unlocking a streamlined and efficient development and deployment experience.

Compose simplifies the control of your entire application stack, making it easy to manage services, networks, and volumes in a single YAML configuration file. Then, with a single command, you create and start all the services from your configuration file.


Example:
1️⃣ Project structure
```
python-demo-service/
├── app.py
├── requirements.txt
├── Dockerfile
└── docker-compose.yml
```

2️⃣ app.py (tiny Python API)
```py

from flask import Flask
import sqlite3

app = Flask(__name__)

@app.route("/")
def hello():
    conn = sqlite3.connect("data.db")
    cur = conn.cursor()
    cur.execute("CREATE TABLE IF NOT EXISTS visits (count INTEGER)")
    cur.execute("INSERT INTO visits VALUES (1)")
    conn.commit()
    cur.execute("SELECT COUNT(*) FROM visits")
    count = cur.fetchone()[0]
    conn.close()

    return f"Hello Docker Compose 👋 Visits: {count}"

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)
```


3️⃣ Dockerfile

```Dockerfile

FROM python:3.11-slim

WORKDIR /app

COPY requirements.txt .
RUN pip install -r requirements.txt

COPY app.py .

CMD ["python", "app.py"]
```


4️⃣ **docker-compose.yml (this is the magic)**

```yml
version: "3.9"

services:
  web:
    build: .
    ports:
      - "5000:5000"
    volumes:
      - ./data:/app
```

### Basic Docker Compose Commands
- `docker compose up`: Creates and starts the containers defined in the Compose file. Use -d to run them in the background (detached mode).
- `docker compose down`: Stops and removes all containers and networks created by up. Use -v to also remove declared volumes.
- `docker compose ps`: Lists the current status of all containers in the project.


---
<br>
<br>
<br>
<br>
<br>

---

<br>
<br>
<br>
<br>


<img src="https://raw.githubusercontent.com/marwin1991/profile-technology-icons/refs/heads/main/icons/kubernetes.png" width="100px">

## Kubernetes - Container Orchestration

Kubernetes, aka K8s, is an open source system for automating deployment, scaling, and management of containerized applications. It was initially developed by Google (predecesor -> Borg)
The open source project is hosted by the Cloud Native Computing Foundation (CNCF).


> ### Did you know ?
> Kubernetes is greek for helmsman (a person who steers a ship or boat).

### Why did we require K8s ?
In real world applications, we rarely work with a single container, in microservice architecture we work with multiple services, dbs, message queues etc. 


### Features of Kubernetes
- Self Healing
- Garbage Collection
- Cloud Agnostic (Independent)
- Reconciliation (Continous Loop)
- Secret/Config Management
- Auto-scaling


### Componenets of Kubernetes


![architecture gif](https://devtron.ai/blog/content/images/2024/09/2-ezgif.com-optimize.gif)


# References

[Medium Kubernetes Tutorial for Beginners](https://praveendandu24.medium.com/kubernetes-tutorial-for-beginners-mastering-the-basics-in-1-hour-332db7b5916b)