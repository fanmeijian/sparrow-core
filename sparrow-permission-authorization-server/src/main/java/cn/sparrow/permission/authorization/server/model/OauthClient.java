package cn.sparrow.permission.authorization.server.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
@Table(name = "spr_oauth_client")
@NamedQuery(name = "OauthClient.findAll", query = "SELECT o FROM OauthClient o")
public class OauthClient implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "CLIENT_ID")
  private String clientId;

  @Column(name = "ACCESS_TOKEN_VALIDITY")
  private int accessTokenValidity;

  private byte autoapprove;

  @Column(name = "CLIENT_SECRET")
  private String clientSecret;

  @Column(name = "GRANT_TYPE")
  private String grantType;

  @Column(name = "REDIRECT_URI")
  private String redirectUri;

  @Column(name = "REFRESH_TOKEN_VALIDITY")
  private int refreshTokenValidity;
}
