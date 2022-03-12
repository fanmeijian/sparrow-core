for dev use, set hibernate.hbm2ddl.auto to create, create-drop, or update, so Envers can create them automatically for auditing log

for production use org.hibernate.tool.EnversSchemaGenerator to export the complete database schema programmatically

or
use a Maven plugin for generating a database schema from our mappings (such as Juplo) to export Envers schema (works with Hibernate 4 and higher)

how to use it:
Properties hibernateProperties = new Properties(); 
hibernateProperties.setProperty(
  "org.hibernate.envers.audit_table_suffix", "_AUDIT_LOG"); 
sessionFactory.setHibernateProperties(hibernateProperties);

A full listing of available properties can be found in https://docs.jboss.org/envers/docs/#configuration