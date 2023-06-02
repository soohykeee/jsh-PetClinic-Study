package kr.co.jshpetclinicstudy.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import kr.co.jshpetclinicstudy.persistence.entity.enums.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AttributeOverride(name = "id", column = @Column(name = "member_id", length = 4))
@Getter
@NoArgsConstructor
@Table(name = "tbl_members")
public class Member extends BaseEntity{

    @Column(name = "identity", unique = true)
    @NotNull
    private String identity;

    @Column(name = "password")
    @NotNull
    private String password;

    @Column(name = "name", length = 100)
    @NotNull
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_role")
    private Role role;

    @Builder
    public Member(String name,
                  String identity,
                  String password,
                  Role role) {
        this.name = name;
        this.identity = identity;
        this.password = password;
        this.role = role;
    }
}
