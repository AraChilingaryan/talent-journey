package com.disqo.onboarding_flow_service.persistance.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "mentor",  schema = "onboarding_service_db")
public class Mentor extends User{


}
