package com.example.kiemtrajpa.entity;

import com.example.kiemtrajpa.dto.UserDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@SqlResultSetMappings(value = {
        @SqlResultSetMapping(
                name = "useInfo", // tên kết quả ở bước 1
                classes = @ConstructorResult(
                        targetClass = UserDto.class,
                        columns = {
                                @ColumnResult(name = "id", type = Integer.class),
                                @ColumnResult(name = "name", type = String.class),
                                @ColumnResult(name = "email", type = String.class)
                        }
                )
        )
})
@NamedNativeQuery(
        name = "getUserDto",
        resultSetMapping = "useInfo",
        query = "select u.id, u.name, u.email from user u")

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String email;
    private String password;

}