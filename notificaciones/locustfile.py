from locust import HttpUser, task, between
import random

class PublicacionesUser(HttpUser):
    wait_time = between(1, 3)  # Tiempo entre peticiones (1-3 segundos)
    host = "http://localhost:8080"  # URL base de ms-publicaciones

    @task(3)  # Mayor probabilidad para libros (3:1)
    def crear_libro(self):
        autores = [1, 2, 3]  # IDs de autores existentes en tu BD
        libro = {
            "titulo": f"Libro de prueba {random.randint(1, 10000)}",
            "genero": random.choice(["Novela", "Ciencia Ficción", "Técnico"]),
            "isbn": f"978-{random.randint(100, 999)}-{random.randint(10, 99)}-{random.randint(1000, 9999)}",
            "numeroPaginas": random.randint(100, 500),
            "editorial": "Editorial de Prueba",
            "anioPublicacion": random.randint(2000, 2023),
            "resumen": "Libro generado automáticamente para pruebas",
            "idAutor": random.choice(autores)
        }
        self.client.post("/libros", json=libro)

    @task(1)  # Menor probabilidad para papers
    def crear_paper(self):
        autores = [1, 2, 3]  # IDs de autores existentes
        paper = {
            "titulo": f"Paper científico {random.randint(1, 10000)}",
            "doi": f"10.1234/abc.{random.randint(1000, 9999)}",
            "conferencia": random.choice(["CONF-2023", "IEEE-Spring", "DevOps Summit"]),
            "url": "https://example.com/paper",
            "tipoPaper": random.choice(["Investigación", "Revisión", "Caso de Estudio"]),
            "tipoPublicacion": "Científico",
            "isbn": f"978-{random.randint(100, 999)}-{random.randint(10, 99)}-{random.randint(1000, 9999)}",
            "anioPublicacion": random.randint(2018, 2023),
            "resumen": "Paper generado automáticamente",
            "editorial": "Science Publications",
            "idAutor": random.choice(autores)
        }
        self.client.post("/papers", json=paper)