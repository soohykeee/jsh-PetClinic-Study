package kr.co.jshpetclinicstudy.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import kr.co.jshpetclinicstudy.persistence.entity.enums.Role;
import kr.co.jshpetclinicstudy.service.model.request.MemberRequestDto;
import kr.co.jshpetclinicstudy.service.model.request.OwnerRequestDto;
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
    @Column(name = "role")
    private Role role;

    @Column(name = "refresh_token")
    private String refreshToken;

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

    public void updateMember(MemberRequestDto.UPDATE update) {
        this.name = update.getName();
        this.role = Role.valueOf(update.getRole());
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
