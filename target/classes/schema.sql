-- Tabla de usuarios
CREATE TABLE IF NOT EXISTS usuarios (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    avatar VARCHAR(255),
    activo BOOLEAN DEFAULT true,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ultima_conexion TIMESTAMP
);

-- Tabla de salas
CREATE TABLE IF NOT EXISTS salas (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    creador_id INTEGER REFERENCES usuarios(id),
    publica BOOLEAN DEFAULT true,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de mensajes
CREATE TABLE IF NOT EXISTS mensajes (
    id SERIAL PRIMARY KEY,
    sala_id INTEGER REFERENCES salas(id) ON DELETE CASCADE,
    usuario_id INTEGER REFERENCES usuarios(id),
    contenido TEXT NOT NULL,
    tipo VARCHAR(20) DEFAULT 'texto',
    fecha_envio TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    leido BOOLEAN DEFAULT false
);

-- Índices para mejorar el rendimiento
CREATE INDEX IF NOT EXISTS idx_mensajes_sala ON mensajes(sala_id);
CREATE INDEX IF NOT EXISTS idx_mensajes_usuario ON mensajes(usuario_id);
CREATE INDEX IF NOT EXISTS idx_mensajes_fecha ON mensajes(fecha_envio);