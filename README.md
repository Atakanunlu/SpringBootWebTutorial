# 🚀 Spring Boot Employee Management REST API

Modern, validasyon destekli ve global response / exception handling yapısına sahip bir Employee Management Backend projesi.

---

## 🧩 Kullanılan Teknolojiler

- Java 17  
- Spring Boot 3  
- Spring Data JPA  
- Spring Validation  
- H2 Database (file based)  
- ModelMapper  
- Lombok  
- Reflection API (PATCH işlemleri için)

---


---

## 🔥 Özellikler

- Global API response yapısı (ApiResponse)
- Global exception handling (@RestControllerAdvice)
- Input validation & custom annotation
- DTO – Entity dönüşümleri (ModelMapper)
- PATCH ile partial update desteği
- H2 file-based database kullanımı
- Validation hataları için detaylı subErrors alanı

---

## 🌐 API Endpoints

| Method | Endpoint | Açıklama |
|-------|----------|----------|
| GET | /employees | Tüm çalışanları getir |
| GET | /employees/{id} | ID ile çalışan getir |
| POST | /employees | Yeni çalışan ekle |
| PUT | /employees/{id} | Çalışanı tamamen güncelle |
| PATCH | /employees/{id} | Alan bazlı güncelleme |
| DELETE | /employees/{id} | Çalışanı sil |

---

## 📤 Örnek POST Request

```json
{
  "name": "Atakan",
  "email": "atakan@gmail.com",
  "age": 25,
  "role": "ADMIN",
  "salary": 5000.75,
  "dateOfJoining": "2023-08-12",
  "isActive": true
}
⚠ Validation Kuralları
Alan	Kural
name	2-15 karakter
email	Geçerli email formatı
age	18-65
role	Sadece USER veya ADMIN
salary	100.50 – 10000.99
isActive	true olmak zorunda

🛡 Global Error Response Örneği
{
  "timeStamp": "02:41:12 09-01-2026",
  "data": null,
  "error": {
    "status": "BAD_REQUEST",
    "message": "Input validation failed",
    "subErrors": [
      "Email shoulbe be a valid email",
      "Age of employee cannot be less than 18"
    ]
  }
}
