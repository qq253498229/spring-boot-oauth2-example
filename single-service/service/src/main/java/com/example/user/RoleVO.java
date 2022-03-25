package com.example.user;

import com.example.generator.model.Resource;
import com.example.generator.model.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class RoleVO extends Role {
    private List<Resource> resourceList;
}
