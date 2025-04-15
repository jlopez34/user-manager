package com.smartjob.managing.profile.repository.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "profiles")
@EntityListeners(AuditingEntityListener.class)
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", updatable = false, nullable = false)
    private UUID idProfile;

    @Size(max = 100)
    @NotNull
    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    @Column(name = "EMAIL", nullable = false, length = 100)
    private String email;

    @Column(name = "PASSWORD", nullable = false, length = 50)
    private String password;

    @CreationTimestamp
    @Column(name = "CREATED", nullable = false)
    private Date created;

    @UpdateTimestamp
    @Column(name = "MODIFIED", nullable = false)
    private Date modified;

    @Column(name = "LAST_LOGIN", nullable = false)
    private Date lastLogin;

    @Column(name = "TOKEN", nullable = false, length = 200)
    private String token;

    @Column(name = "ACTIVE", nullable = false, length = 1)
    private Boolean active;

    @OneToMany(mappedBy = "profile")
    @ToString.Exclude
    private Set<Phone> userPhonesByIdUser  = new LinkedHashSet<>();

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Profile that = (Profile) o;
        return getIdProfile() != null && Objects.equals(getIdProfile(), that.getIdProfile());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
