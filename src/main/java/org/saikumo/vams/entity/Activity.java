package org.saikumo.vams.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDateTime;
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

	private Long organizerId;

	private String organizerName;

	private String name;

	private String description;

	private String status;

	private String location;

	private Long timestamp;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private List<User> users;

	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
	@Fetch(FetchMode.SUBSELECT)
	private List<Comment> comments;
}
