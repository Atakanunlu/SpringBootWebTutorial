
# Spring Boot Web API Playground 🧪

**Global Response – Exception – Validation – DTO Mapping**

Bu repository, **Spring Boot REST API geliştirme sürecinde** en kritik ama genelde yüzeysel geçilen konuları **derinlemesine öğrenmek ve denemek** amacıyla hazırlanmış bir **çalışma / playground** reposudur.

> ❗ Bu bir production projesi değildir.
> Amaç: **Spring Boot Web katmanının nasıl çalıştığını gerçekten anlamak.**

---

## 🎯 Çalışmanın Amaçları

Bu çalışmada özellikle şu sorulara cevap arandı:

* Controller’dan dönen response’lar nasıl **tek formatta** toplanır?
* Exception’lar merkezi olarak nasıl yönetilir?
* Validation hataları nasıl **detaylı** yakalanır?
* Custom validation annotation nasıl yazılır?
* DTO ↔ Entity dönüşümü nasıl yapılır?
* PATCH (partial update) nasıl implemente edilir?
* Reflection ile alan güncelleme mantığı nasıl çalışır?

---

## 🧱 Kullanılan Teknolojiler

* Java 17+
* Spring Boot
* Spring Web
* Spring Validation (Jakarta Validation)
* Spring Data JPA
* H2 Database
* ModelMapper
* Lombok
* JUnit

---

## 📦 Katmanlı Mimari Yapı

```
controller
service
repository
dto
entity
advice (global response & exception)
annotations (custom validation)
exceptions
config
```

---

## 🌍 Global Response Yapısı

### ApiResponse (Generic Wrapper)

```java
public class ApiResponse<T> {
    private LocalDateTime timeStamp;
    private T data;
    private ApiError error;
}
```

✔️ Tüm endpoint’ler **tek bir response formatında** döner
✔️ Başarılı response → `data`
✔️ Hatalı response → `error`

---

### GlobalResponseHandler

```java
@RestControllerAdvice
public class GlobalResponseHandler implements ResponseBodyAdvice<Object>
```

**Ne yapar?**

* Controller’dan dönen her response’u otomatik olarak `ApiResponse` içine alır
* Eğer response zaten `ApiResponse` ise tekrar sarmalamaz

📌 Sonuç:

> Controller’lar **temiz**, response standardizasyonu **merkezi**

---

## 🚨 Global Exception Handling

### ApiError Yapısı

```java
public class ApiError {
    private HttpStatus status;
    private String message;
    private List<String> subErrors;
}
```

---

### GlobalExceptionHandler

Yakalanan exception türleri:

* `ResourceNotFoundException`
* `MethodArgumentNotValidException`
* `Exception` (fallback)

```java
@RestControllerAdvice
public class GlobalExceptionHandler
```

✔️ HTTP status kontrolü
✔️ Validation hatalarını liste halinde döndürme
✔️ Tek noktadan yönetim

---

## ✅ Validation Çalışmaları

### DTO Üzerinde Validation

```java
@NotBlank
@Size(min = 2, max = 15)

@Email
@Min(18)
@Max(65)

@Positive
@Digits(integer = 6, fraction = 2)

@PastOrPresent
@AssertTrue
```

📌 Amaç:

> Veriyi **controller’a bile sokmadan** hatayı yakalamak

---

## 🧩 Custom Validation Annotation

### @EmployeeRoleValidation

```java
@Constraint(validatedBy = EmployeeRoleValidator.class)
public @interface EmployeeRoleValidation
```

### Validator

```java
public class EmployeeRoleValidator
        implements ConstraintValidator<EmployeeRoleValidation, String>
```

✔️ Enum yerine dinamik kontrol
✔️ Reusable validation mantığı
✔️ Regex yerine okunabilir kod

---

## 🔄 DTO ↔ Entity Mapping

### ModelMapper Kullanımı

```java
@Bean
public ModelMapper getModelMapper()
```

Kullanım alanları:

* Create
* Update
* Get
* Partial Update sonrası dönüş

📌 Amaç:

> Manuel setter yazmadan **clean mapping**

---

## 🧠 Employee CRUD API

### Endpoint’ler

| Method | Endpoint        | Açıklama           |
| ------ | --------------- | ------------------ |
| GET    | /employees      | Tüm çalışanlar     |
| GET    | /employees/{id} | ID’ye göre çalışan |
| POST   | /employees      | Yeni çalışan       |
| PUT    | /employees/{id} | Tam güncelleme     |
| PATCH  | /employees/{id} | Kısmi güncelleme   |
| DELETE | /employees/{id} | Silme              |

---

## ✏️ PATCH (Partial Update) Mantığı

```java
Map<String, Object> updates
```

```java
ReflectionUtils.findField(...)
ReflectionUtils.setField(...)
```

✔️ Sadece gönderilen alanlar güncellenir
✔️ DTO zorunluluğu yok
✔️ Reflection çalışma mantığı gözlemlenir


---

## 🗄️ Database & Config

* H2 File-based database
* H2 Console aktif
* `ddl-auto=update`
* SQL log açık

```properties
spring.h2.console.enabled=true
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

---

## 🧪 Öğrenilen Ana Kavramlar

* Global Response Standardization
* Centralized Exception Handling
* Jakarta Validation Lifecycle
* Custom Constraint & Validator
* DTO – Entity separation
* Reflection API
* PATCH vs PUT farkı
* Controller temizliği

---


Bu repository bir **öğrenme defteri** gibidir.
Kodlar özellikle:

* Okunabilir
* Açıklayıcı
* Deneysel

şekilde yazılmıştır.

