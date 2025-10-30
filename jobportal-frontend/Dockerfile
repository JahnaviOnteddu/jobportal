# # Stage 1: Build the Angular app
# FROM node:18-alpine as build

# WORKDIR /app

# # Copy package files and install dependencies
# COPY package*.json ./
# RUN npm install

# # Copy all project files and build the app
# COPY . .
# RUN npm run build --prod

# # Stage 2: Serve the app using Nginx
# FROM nginx:alpine

# # Copy built Angular files to Nginx html directory
# COPY --from=build /app/dist/JOBPORTAL-FRONTEND /usr/share/nginx/html

# # Expose port 80 for the container
# EXPOSE 80

# # Start Nginx
# CMD ["nginx", "-g", "daemon off;"]


# # Stage 1: Build the frontend application
# FROM node:22.21-alpine AS build
# WORKDIR /app
# COPY package*.json ./
# RUN npm install
# COPY . .
# RUN npm run build -- --configuration=production

# # Stage 2: Serve with Nginx
# FROM nginx:alpine
# # COPY nginx.conf /etc/nginx/nginx.conf
# COPY --from=build /app/dist/JOBPORTAL-FRONTEND /usr/share/nginx/html
# # COPY nginx.conf /etc/nginx/nginx.conf
# EXPOSE 80
# CMD ["nginx", "-g", "daemon off;"]

# Stage 1: Build Angular app
# FROM node:22.21-alpine AS build
# WORKDIR /app
# COPY package*.json ./
# RUN npm install
# COPY . .
# RUN npm run build -- --configuration production

# # Stage 2: Nginx server
# FROM nginx:alpine
# COPY --from=build /app/dist/JOBPORTAL-FRONTEND /usr/share/nginx/html
# COPY nginx.conf /etc/nginx/conf.d/default.conf

# EXPOSE 80
# CMD ["nginx", "-g", "daemon off;"]

# Stage 1: Build Angular App
FROM node:22.21-alpine AS build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build -- --configuration production

# Stage 2: Serve with Nginx
FROM nginx:alpine

# Remove default nginx config
RUN rm -rf /etc/nginx/conf.d/default.conf

# Copy built Angular files
COPY --from=build /app/dist/JOBPORTAL-FRONTEND /usr/share/nginx/html

# Copy custom nginx config
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]


