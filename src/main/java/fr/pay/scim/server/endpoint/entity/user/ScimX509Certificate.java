package fr.pay.scim.server.endpoint.entity.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data()
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
@Schema(name = "x509Certificate")
public class ScimX509Certificate {

	@Schema(accessMode = Schema.AccessMode.READ_WRITE)
	private byte[] value;
	
	
	@Schema(accessMode = Schema.AccessMode.READ_WRITE)
	private String display;
	 
	
	@Schema(accessMode = Schema.AccessMode.READ_WRITE)
	private String type;
	

	@Schema(accessMode = Schema.AccessMode.READ_WRITE)
	private Boolean primary;
	
}
