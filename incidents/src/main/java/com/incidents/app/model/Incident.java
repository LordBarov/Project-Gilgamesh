package com.incidents.app.model;


import com.incidents.app.model.dictionaries.Category;
import com.incidents.app.model.dictionaries.PriorityLevel;
import com.incidents.app.model.dictionaries.Tag;
import com.incidents.app.model.dictionaries.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "incidents")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Incident extends CreatedUpdatedMappedSuperClass{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description",columnDefinition = "TEXT")
    private String description;

    @OneToOne
    @JoinColumn(name = "priority_id")
    private PriorityLevel priorityLevel;

    @OneToOne
    @JoinColumn(name="category_id")
    private Category category;

    @ManyToMany
    @JoinTable(
            name = "incident_types",
            joinColumns =  @JoinColumn(name = "incident_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id")
    )
    private List<Type> types;


    @ManyToMany
    @JoinTable(
            name = "incident_tags",
            joinColumns =  @JoinColumn(name = "incident_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;

}
