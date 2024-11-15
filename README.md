Failover recovery in a **Spring Boot/Angular project** ensures that the application remains functional and accessible even when part of the system fails. Here's a step-by-step guide to implement failover recovery:

---

### **1. Understand the Types of Failures**
- **Backend Service Failure:** A microservice or database goes down.
- **Frontend Service Failure:** Angular frontend can't connect to the backend or the CDN fails.
- **Infrastructure Failure:** Server, container, or network issues.

---

### **2. Backend: Spring Boot Failover Recovery**
#### a. **Enable Circuit Breaker with Resilience4j or Spring Cloud**
Use **Resilience4j** for handling failures gracefully:
- Add dependencies:
  ```xml
  <dependency>
      <groupId>io.github.resilience4j</groupId>
      <artifactId>resilience4j-spring-boot2</artifactId>
      <version>1.7.1</version>
  </dependency>
  ```
- Annotate service methods:
  ```java
  @Service
  public class MyService {

      @CircuitBreaker(name = "myService", fallbackMethod = "fallbackMethod")
      public String callExternalService() {
          // Logic to call external service
      }

      public String fallbackMethod(Throwable t) {
          return "Fallback response";
      }
  }
  ```

#### b. **Load Balancer**
Use **Spring Cloud LoadBalancer** for automatic failover between replicas:
- Add dependencies:
  ```xml
  <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-loadbalancer</artifactId>
  </dependency>
  ```

#### c. **Retry Mechanism**
Enable retries for transient errors:
- Configuration in `application.yml`:
  ```yaml
  resilience4j.retry:
    instances:
      myService:
        maxAttempts: 3
        waitDuration: 200ms
  ```

#### d. **Failover Database Setup**
Use primary/secondary database configurations:
- Define multiple data sources in `application.yml`:
  ```yaml
  spring:
    datasource:
      url: jdbc:mysql://primary-db-url
    failover-datasource:
      url: jdbc:mysql://secondary-db-url
  ```

---

### **3. Frontend: Angular Failover Recovery**
#### a. **Retry Failed HTTP Calls**
Use **HttpClient Interceptors**:
```typescript
@Injectable()
export class RetryInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(
      retry(3), // Retry up to 3 times
      catchError((error) => {
        console.error('Failed HTTP call:', error);
        return throwError(() => new Error('Failed HTTP call'));
      })
    );
  }
}
```

#### b. **Fallback UI/Offline Mode**
- Implement **Service Workers** for offline caching:
  ```bash
  ng add @angular/pwa
  ```
- Show fallback UI when backend is unavailable:
  ```typescript
  if (!this.backendAvailable) {
    this.message = 'Service is currently unavailable. Please try again later.';
  }
  ```

---

### **4. Monitoring and Alerts**
- Use **Spring Boot Actuator** to monitor system health:
  ```xml
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-actuator</artifactId>
  </dependency>
  ```
- Enable health checks:
  ```yaml
  management:
    endpoints:
      web:
        exposure:
          include: health
  ```

---

### **5. Infrastructure Setup**
#### a. **Use Kubernetes or Docker Swarm**
- Deploy replicas for Spring Boot services.
- Use health probes for automatic failover:
  ```yaml
  livenessProbe:
    httpGet:
      path: /actuator/health
      port: 8080
    initialDelaySeconds: 3
    periodSeconds: 5
  ```

#### b. **Frontend Failover with CDN**
- Use multiple CDN providers to host Angular assets (e.g., Cloudflare, AWS S3).
- Configure fallback URLs in the Angular service worker.

---

### **6. Testing Failover**
- Simulate backend and frontend failures.
- Test retries, circuit breakers, and fallback mechanisms.
- Use chaos engineering tools like **Chaos Monkey** to induce failures and verify recovery.

---

By combining these backend and frontend strategies with robust infrastructure, you can achieve reliable failover recovery in your Spring Boot/Angular project.

-----------------

Failover is the ability to switch automatically and seamlessly to a reliable backup system. When a component or primary system fails, either a standby operational mode or redundancy should achieve failover and lessen or eliminate negative impact on users.

To achieve redundancy upon the abnormal failure or termination of a formerly active version, a standby database, system, server, or other hardware component or network must always stand ready to automatically switch into action. In other words, all backup techniques including standby computer server systems must themselves be immune to failure, because failover is critical to disaster recovery (DR).

What is a failover?
Failover automation in servers includes pulse or heartbeat conditions. That is, heartbeat cables connect two servers or multiple servers in a network with the primary server always active. As long as the heartbeat continues or it perceives the pulse, the secondary server merely rests.

However, should the secondary server perceive any change in the pulse from the primary failover server, it will initiate its instances and take over the primary server’s operations. It will also message the technician or data center requesting that they bring the primary server back online. Some systems, called automated with manual approval configuration, simply alert the technician or data center instead, requesting the change to the server take place manually.

Virtualization simulates a computer environment using a virtual machine or pseudo machine running host software. In this way, the failover process can be independent of the physical hardware components of computer server systems.

How does failover work?
Active-active and active-passive or active-standby are the most common configurations for high availability (HA). Each implementation technique achieves failover in a different way, although both improve reliability.

Typically, at least two nodes actively and simultaneously running the same sort of service comprise an active-active high availability cluster. The active-active cluster distributes workloads across all the nodes more evenly, preventing any single node from overloading and achieving load balancing. And because more nodes remain available, throughput and response times improve. To ensure the HA cluster operates seamlessly and achieves redundancy, the individual configurations and settings of the nodes should be identical.

In contrast, in an active-passive cluster, although there must be at least two nodes, not all of them are active. In a two node system with the first node active, the second node will remain passive or on standby as the failover server. In this standby operational mode, it can remain ready should the active, primary server stop functioning to serve as a backup. However, unless there is a failure, clients only connect to the active server.

Just as in the active-active cluster, both servers in the active-standby cluster must be configured with the very same settings. This way, clients cannot perceive any change in service, even if the failover router or server must take over.

Clearly, in an active-standby cluster although the standby node is always running, actual utilization approaches zero.

In an active-active cluster, utilization of both nodes nears half and half— although each node can handle the entire load alone. However, this also means that node failure can cause performance to degrade if one active-active configuration node handles more than half of the load consistently.

Outage time during a failure is virtually zero with an active-active HA configuration, because both paths are active. With an active-passive configuration, outage time has the potential to be greater, as the system must switch from one node to the other, which requires time.

What is a failover cluster?
A failover cluster is a set of computer servers that provide fault tolerance (FT), continuous availability (CA), or high availability (HA) together. Failover cluster network configurations may use virtual machines (VMs), physical hardware only, or both.

If one of the servers in a failover cluster goes down, this triggers the failover process. Instantly sending the failed component’s workload to another node in the cluster, this prevents downtime.

Providing either HA or CA for applications and services is a failover cluster’s primary goal. Also known as fault tolerant (FT) clusters, CA clusters eliminate downtime when main or primary systems fail, enabling end users to keep using applications and services without interruptions or timeouts.

In contrast, despite a potential brief interruption in service, HA clusters offer minimal downtime, automatic recovery, and no data loss. The recovery process in HA clusters can be configured using failover cluster manager tools, which are included as part of most failover cluster solutions.

In a broader sense, a cluster is two or more nodes or servers, usually connected both physically with cables and via software. Additional clustering technologies such as parallel or concurrent processing, load balancing, and cloud storage solutions are included in some failover implementations.

Internet failover is essentially a redundant or secondary internet connection to be used as a failover link in case of a failure. This can be thought of as another piece of failover capability in servers.

What is an application server failover?
Application servers are simply servers that run applications. This means that application server failover is a failover strategy to protect these types of servers.

At a minimum, these application servers should have unique domain names, and ideally they should run on different servers. Failover cluster best practices typically include application server load balancing.

What is Failover testing?
Failover testing validates a system’s capacity during a server failure to allocate sufficient resources toward recovery. In other words, failover testing assesses failover capability in servers.

The test will determine whether the system has the capacity in the event of any kind of abnormal termination or failure to handle necessary extra resources and move operations to backup systems. For instance, failover and recovery testing determines the ability of the system to manage and power an additional CPU or multiple servers once it achieves a threshold for performance — one often breached during critical failures. This highlights the important relationship between failover testing, resilience, and security.

What is failover and failback?
In computing and related technologies such as networking, failover is the process of switching operations to a backup recovery facility. The backup site in failover is generally a standby or redundant computer network, hardware component, system, or server, often in a secondary disaster recovery (DR) location. Typically, failover involves using a failover tool or failover service of some type to temporarily halt and restart operations from a remote location.

A failback operation involves returning production to its original location after a scheduled maintenance period or a disaster. It is the return from standby to fully functional.

Typically, systems designers offer failover capability in systems, servers, or networks demanding CA, HA, or a high level of reliability. Failover practices have also become less reliant on physical hardware with little or no disruption in service thanks to the use of virtualization software.


Does Druva offer a cloud failover strategy?
With single-click failback to the primary site, post-event mitigation, Druva offers something unique in the industry. A simple, identical configuration of your primary and failover VMs is the first step. Data transfer starts once virtual machine disks are attached, and once transfer is completed, DNS connections are redirected and primary VMs are rebooted.

Now more than ever, threats from remote worker data and cyber attackers are increasing. Data is the fuel your enterprise needs; protect it with a robust DR strategy. Empower your approach by leveraging the global reach and scalability of Druva, built on AWS.

Watch the video below to learn more about the meaning of failover, and explore Druva DRaaS here to find out how the cloud ensures your data is always on, always safe.
