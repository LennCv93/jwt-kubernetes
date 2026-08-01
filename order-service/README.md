# KUBERNETES CON SPRING BOOT Y DOCKER

### 1.- Pre-requisitos

- Compilar para verificar
```
cd order-service
mvn clean compile
```
### 2- Iniciar PostgreSQL para productdb

#### Iniciar PostreSQL

```
# Desde el directorio raíz del proyecto
docker-compose -f ../docker-compose.yml up -d

```

### 3.- Dockerizar order-service

#### Compilar con perfil Kubernetes

```
mvn clean package -DskipTests
```

#### Constuir imagen

```
# Construir imagen
docker build -t order-service:1.0 .

# Este proceso toma 2-3 minutos la primera vez
# Ver progreso: [1/2] STEP X/Y...

# Verificar imagen creada
```

### 4.- Desplegar en Kubernetes

#### Crear Namespace en Kubernetes

- Aplicar namespace
```
kubectl apply -f k8s/00-namespace.yaml

```
- Verificar namespace
```
kubectl get namespaces  

```

#### Crear ConfigMap

- Aplicar ConfigMap
```
kubectl apply -f k8s/01-configmap.yaml

```
- Verificar ConfigMap
```
kubectl get configmap -n product-service

```

#### Crear Secret
```
# Aplicar
kubectl apply -f k8s/02-secret.yaml

```

#### Desplegar Product-Service

- Aplicar Deployment
```
kubectl apply -f k8s/03-deployment.yaml
```


- En caso necesites redesplegar (por ejemplo, después de corregir un error en el Deployment):
```
 kubectl rollout restart deployment order-service -n order-service
```


- Verificar pods
```
kubectl get pods -n order-service 
```

- Ver logs
```
# Ver logs
kubectl logs -f <POD_NAME> -n order-service

# Ver descripción completa del pod
kubectl describe pod <POD_NAME> -n order-service

```

- Verificar variables de entorno

#### Exponer con Service

- Aplicar Service

```
kubectl apply -f k8s/04-service.yaml

```

- Verificar Service
```

kubectl get service -n product-service

```

- Probar order-service
```
# Health check
curl http://localhost:30083/actuator/health

# Output esperado:
# {"status":"UP"}
```
# Listar productos
```
curl http://localhost:30083/api/orders
```