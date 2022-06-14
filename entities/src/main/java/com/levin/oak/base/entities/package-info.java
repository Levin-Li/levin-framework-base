
@GenericGenerator(name = "guid", strategy = "org.hibernate.id.GUIDGenerator")
@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
@GenericGenerator(name = "hex_uuid", strategy = "org.hibernate.id.UUIDHexGenerator")
@GenericGenerator(name = "table_gid", strategy = "org.hibernate.id.TableGenerator")
package com.levin.oak.base.entities;

import org.hibernate.annotations.GenericGenerator;