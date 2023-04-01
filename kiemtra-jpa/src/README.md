## Câu 1. 
### TRẢ LỜI :  
Trong JPA @Entity được sử dụng để đánh dấu 1 class là 1 Entity(đối tượng) trong cơ sở dữ liệu, còn @Table được sử dụng để chỉ định tên của 1 bảng trong cơ sở dữ liệu mà Entity này sẽ tương tác.
Vì vậy, thuộc tính `name` trong annotation @Entity  để đặt tên cho Entity(đối tượng) trong cơ sở dữ liệu và thuộc tính `name` trong @Table được sử dunng để chỉ định tên của bảng trong cơ sở dữ liệu.
Nếu bạn không ch định thuộc tính `name` cho annotation @Entity thì mặc định `Hibernate` sẽ sử dụng tên của class làm tên cho Entity, tương tự như @Table `Hibernate` sẽ lấy tên của Entity làm tên cho bảng.
#### Ví dụ: 
```java

```

```java

```

## Câu 2 

### TRẢ LỜI :
Để bật chế độ debug để xem câu lệnh SQL mà Hibernate sẽ sinh ra trong quá trình thực thi, bạn cần bổ sung lệnh sau vào file application.properties:
```jpqlcommunity
spring.jpa.show-sql=true
```

💡 spring.jpa.show-sql: Logging SQL statement được thực thi trong quá trình chạy project


Bạn cũng có thể thêm các tùy chọn khác để điều chỉnh chế độ debug, ví dụ như bật chế độ hiển thị giá trị tham số trong câu lệnh SQL:

```jpqlcommunity
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

💡 spring.jpa.hibernate.ddl-auto: nhận các giá trị dưới đây
- validate: validate schema, không thay đổi database
- update: update schema (đổi cấu trúc entity → thay đổi cấu trúc bảng nhưng không làm mất dữ liệu) ⇒ Khuyến cáo sử dụng
- create: mỗi lần chạy tạo schema mới, xóa toàn bộ dữ liệu cũ
- create-drop: xóa schema cuối phiên sử dụng
- none: không thay đổi database

💡 spring.jpa.properties.hibernate.dialect: Hibernate có thể làm việc với các loại database khác nhau, tuy nhiên mỗi loại database lại có những cú pháp, extensions riêng. Sử dụng tham số “Dialect” để biết được loại database nào đang được sử dụng, như thế các câu lệnh SQL được sinh ra sẽ phù hợp với database. Danh sách: https://stackjava.com/hibernate/cac-loai-sql-dialects-trong-hibernate.html

## Câu 3

### TRẢ LỜI :
Annotation @Column dùng để bổ sung tính chất cho cột ứng với một thuộc tính.

- `name`: tham số này được sử dụng để đổi tên cột trong cơ sở dữ liệu nếu muốn khác với tên thuộc tính.
- `unique`: tham số này chỉ định yêu cầu dữ liệu trong cột phải là duy nhất và không được trùng lặp.
- `nullable`: tham số này buộc trường không được phép để trống (null).
## Câu 4

### TRẢ LỜI :
Có 2 sự kiện mà JPA có thể bắt được. Logic bổ sung như sau:

- `@PrePersist` Ngay trước khi đối tượng Entity lưu xuống CSDL (ngay trước lệnh INSERT)

Ví Dụ: 
```java
package com.example.kiemtrajpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "updatedDate")
    private LocalDateTime updatedDate;

    @PrePersist
    private void prePersist() {
        if (createdDate == null) {
            createdDate = LocalDateTime.now();
        }
    }

}
```


- `@PreUpdate`: Ngay trước khi đối tượng Entity cập nhật xuống CSDL (ngay trước lệnh UPDATE)

Ví dụ : 
```java
package com.example.kiemtrajpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;
    

    @Column(name = "updatedDate")
    private LocalDateTime updatedDate;
    
    @PreUpdate
    private void preUpdate() {
        if (updatedDate == null) {
            updatedDate = LocalDateTime.now();
        }
    }

}
```


## Câu 5

### TRẢ LỜI :
JpaRepository là một interface có sẵn trong thư viện JPA, nó cũng cấp các mẫu hàm thuận tiện cho thao tác dữ liệu.
Cụ thể: 

`JpaRepository` kế thừa từ hai interface:

- `PagingAndSortingRepository`: cung cấp các phương thức để phân trang và sắp xếp kết quả truy vấn.
- `CrudRepository`: cung cấp các phương thức cơ bản cho việc tạo, đọc, cập nhật và xóa dữ liệu.

Vì vậy, JpaRepository kế thừa tất cả các phương thức của hai interface này và cung cấp thêm một số phương thức bổ sung cho việc làm việc với JPA.


## Câu 6

### TRẢ LỜI :

Đầu tiên ta cần tạo 1 Entity là `Post`
```java: Post.java
package com.example.kiemtrajpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

}
```

Tiếp đến ta tạo 1 repository 
```java
package com.example.kiemtrajpa.repository;

import com.example.kiemtrajpa.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
```
Trên là 1 interface repository thao tác với Entity tên là Post, kiểu dữ liệu trường Identity là long, tuân thủ interface JpaRepository.

## Câu 7

### TRẢ LỜI :
Khi đã chọn một cột là Identity dùng @Id để đánh dấu thì không nhất thiết phải sử dụng annotation `@Column(unique=true)` để xác định giá trị là `unique `.
Tuy nhiên, nếu muốn chắc chắn rằng dữ liệu trong cột đó không được trùng lặp và là duy nhất thì việc sử dụng `@Column(unique=true)` vẫn là 1 sự lựa chọn tốt.
Nó sẽ đảm bảo rằng cơ sở dữ liệu sẽ không chứa hai bản ghi nào có giá trị giống nhau cho cột đó.

## Câu 8

### TRẢ LỜI :

Đầu tiên ta cần tạo 1 Entity Employee với các fields `id`, `emailAddress`, `firstName`, `lastName`.

```java: Employee.java
package com.example.kiemtrajpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;


    @Column(name = "emailAddress", unique = true)
    private String emailAddress;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;


}

```

Sau đó tạo `repository` thao tác với Entity `Employee` và các method như sau: 

```java
package com.example.kiemtrajpa.repository;

import com.example.kiemtrajpa.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

//    Tìm tất cả các Employee theo emailAddress và lastName
    List<Employee> findAllByEmailAddressAndLastName(String emailAddress , String lastName);
//    Tìm tất cả các Employee khác nhau theo firstName hoặc lastName
    List<Employee> findAllByFirstNameOrLastName(String firstName , String lastName);
//    Tìm tất cả các Employee theo lastName và sắp xếp thứ tự theo firstName tăng dần
    List<Employee> findAllByLastNameOrderByFirstNameAsc(String firstName);
//    Tìm tất cả các Employee theo fistName không phân biệt hoa thường
    List<Employee> findAllByFirstNameIgnoreCase(String firstName);
}
```

## Câu 9

### TRẢ LỜI :

- `@NamedQuery`: được sử dụng để đặt tên và xác định một truy vấn cụ thể, mà có thể được thực thi bằng cách sử dụng tên của truy vấn đó trong mã Java. Điều này giúp giảm thiểu lỗi xảy ra trong mã Java và cho phép chúng ta quản lý truy vấn dễ dàng hơn.

Ví Dụ: 

```java
package com.example.kiemtrajpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "post")
@NamedQuery(name = "findPostByName",
            query = "select p from Post p where p.name = :name")       
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "createdDate")
    private LocalDateTime createdDate;

    @Column(name = "updatedDate")
    private LocalDateTime updatedDate;

    @PrePersist
    private void prePersist() {
        if (createdDate == null) {
            createdDate = LocalDateTime.now();
        }
    }

    @PreUpdate
    private void preUpdate() {
        if (updatedDate == null) {
            updatedDate = LocalDateTime.now();
        }
    }

}
```

Trong ví dụ trên, `@NamedQuery` được sử dụng để đặt tên truy vấn tìm kiếm Post theo `name`, được định nghĩa trong Entity `Post`. Trong truy vấn, tham số `:name` được sử dụng để tìm kiếm theo tên cụ thể.

- `@Query`: được sử dụng để xác định một truy vấn cụ thể bằng cách sử dụng cú pháp SQL hoặc JPQL (Java Persistence Query Language). Truy vấn này có thể được sử dụng trong mã Java để truy xuất và thay đổi dữ liệu.

Ví Dụ:

```java
package com.example.kiemtrajpa.repository;

import com.example.kiemtrajpa.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    
    @Query("select p from Post p where p.name = ?1")
    List<Post> findPostByName(String name);
}
```

Trong ví dụ trên, `@Query` được sử dụng để định nghĩa một truy vấn tìm kiếm Post theo `name`, trong đó tham số đầu vào là `name` được sử dụng để tìm kiếm. Truy vấn này có thể được sử dụng để truy xuất dữ liệu `Post` từ cơ sở dữ liệu.

## Câu 10

### TRẢ LỜI :

Dựa vào dữ liệu Entity ở câu 8 trên ta tạo thêm package `controller` và `service`.
Trong package `controller` ta tạo 1 class `EmployeeController` như sau :

```java
package com.example.kiemtrajpa.controller;


import com.example.kiemtrajpa.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("employee")
    public ResponseEntity<?> findAllEmployeePage(@RequestParam(defaultValue = "1") int page,
                                                 @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(employeeService.findAllEmployeePage(page,pageSize));
    }
}

```
Trong class `EmployeeController` ta tạo 1 Method `findAllEmployeePage` xử lý yêu cầu HTTP GET đến đường dẫn `"/employee"` và trả về 1 danh sách các nhân viên theo yêu cầu của người dùng.

Method có 2 tham số `@RequestParam`, `"page"` và `"pageSize"`, để lấy thông tin về trang và số lượng nhân viên trên mỗi trang. Nếu người dùng không cung cấp giá trị cho các tham số này, thì giá trị mặc định sẽ là `1` cho `"page"` và `10` cho `"pageSize"`.



Sau đó ta tạo 1 class `EmployeeService` trong package `service` như sau để xử lý yêu cầu từ người dùng trong class `EmployeeController` :

```java
package com.example.kiemtrajpa.service;


import com.example.kiemtrajpa.controller.EmployeeController;
import com.example.kiemtrajpa.entity.Employee;
import com.example.kiemtrajpa.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    // 1.Xem danh sách Employee (có phân trang và sắp xếp theo firstName  giảm dần)
    public Page<Employee> findAllEmployeePage(int page, int pageSize) {
        Page<Employee> employees = employeeRepository.findAll(
                PageRequest.of(page,pageSize,Sort.by("firstName").descending()));
        return employees;
    }




}


```

Trong class `EmployeeService` ta đã sử dụng phương thức `findAll()` để lấy toàn bộ danh sách nhân viên trong cơ sở dữ liệu và trả về 1 đối tượng kiểu `Page<Employee>` chứa các thông tin về danh sách các nhân viên , số lượng trang , số phần tử trên mỗi trang và một số thông tin khác.

Ở đây, chúng ta sử dụng `PageRequest.of` để tạo ra một đối tượng `PageRequest`, đối tượng này chứa thông tin về chỉ số trang cần lấy và số lượng phần tử trên mỗi trang, đồng thời sắp xếp theo `firstName` theo thứ tự giảm dần bằng `descending()`.

Cuối cùng, phương thức trả về đối tượng `Page<Employee>` chứa danh sách các nhân viên được lấy từ cơ sở dữ liệu theo yêu cầu của người dùng.


## Câu 11

### TRẢ LỜI :

Đầu tiên ta cần tạo 3 Entity `Product.java` và `Category.java` và `Tag.java` như sau : 

Entity : `Product.java`

```java
package com.example.kiemtrajpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String name;

}
```

Entity : `Category.java`

```java
package com.example.kiemtrajpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String name;
    
}
```

Entity : `Tag.java`

```java
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tag")
public class Tag {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String name;
```

Sau đó bổ sung định nghĩa quan hệ One to Many giữa bảng Category (One) – Product (Many). Chú ý khi một Category xoá, thì không được phép xoá Product, mà chỉ set thuộc tính Category của Product là null như sau : 

Entity `Product.java` sau định nghĩa lại : 

```java
package com.example.kiemtrajpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
```

Entity `Category.java` sau khi định nghĩa lại : 

```java
package com.example.kiemtrajpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "category" , orphanRemoval = false)
    private List<Product> products = new ArrayList<>();

}
```
Trong `Category.java` ta sử dụng annotation `@OneToMany` để chỉnh định mối quan hệ giữa 2 bảng `Product` và `Category`, thuộc tính `mappedBy` để chỉ định tên trường trong Entity `Product` tham chiếu đến Entity `Category`. 
Cuối cùng ta dùng thuộc tính `orphanRemoval = false` để thiết lập khi đối tượng `Product` bị xóa thì đối tượng quan hệ với nó sẽ không bị xóa.

Cuối cùng bổ sung định nghĩa quan hệ `Many to Many` giữa bảng `Tag(Many)` – `Product(Many)` như sau : 

Entity `Tag.java` sau khi định nghĩa lại : 

```java
package com.example.kiemtrajpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String name;

    @ManyToMany
    @JoinTable(name = "tag_products",
            joinColumns = @JoinColumn(name = "tag_id"),
            inverseJoinColumns = @JoinColumn(name = "products_id"))
    private List<Product> products = new ArrayList<>();

}
```

Trong Entity `Tag.java` ta dùng annotation  `@ManyToMany` để thiết lập quan hệ, dùng `@JoinTable` để chỉ định tên bảng trung gian


## Câu 12

### TRẢ LỜI :

Viết câu lệnh query để tìm kiếm UserDto bao gồm các thuộc tính (id, name, email) theo cách sau

Method query + Convert to Dto như sau: 

```java
 @Query("select new com.example.kiemtrajpa.dto.UserDto(u.id,u.name,u.email) from User u")
    List<UserDto> findAllUserDto();
```