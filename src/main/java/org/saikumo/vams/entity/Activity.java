package org.saikumo.vams.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Activity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long mentorId;

	private String name;

	private String description;

	private String status;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private List<User> users;
}
