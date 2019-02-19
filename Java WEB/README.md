### Comparing ```RESOURCE_LOCAL``` and ```JTA``` persistence contexts

#### With ```<persistence-unit transaction-type="RESOURCE_LOCAL">``` YOU are responsible for EntityManager (PersistenceContext/Cache) creating and tracking...

  - You must use the EntityManagerFactory to get an EntityManager
  - The resulting EntityManager instance is a PersistenceContext/Cache
  - An EntityManagerFactory can be injected via the @PersistenceUnit annotation only (not @PersistenceContext)
  - You are not allowed to use @PersistenceContext to refer to a unit of type RESOURCE_LOCAL
  - You must use the EntityTransaction API to begin/commit around every call to your EntityManger
  - Calling entityManagerFactory.createEntityManager() twice results in two separate EntityManager instances and therefor two separate   - PersistenceContexts/Caches.
  - It is almost never a good idea to have more than one instance of an EntityManager in use (don't create a second one unless you've destroyed the first)


#### With ```<persistence-unit transaction-type="JTA">``` the CONTAINER will do EntityManager (PersistenceContext/Cache) creating and tracking...

  - You cannot use the EntityManagerFactory to get an EntityManager
  - You can only get an EntityManager supplied by the container
  - An EntityManager can be injected via the @PersistenceContext annotation only (not @PersistenceUnit)
  - You are not allowed to use @PersistenceUnit to refer to a unit of type JTA
  - The EntityManager given by the container is a reference to the PersistenceContext/Cache associated with a JTA Transaction.
  - If no JTA transaction is in progress, the EntityManager cannot be used because there is no PersistenceContext/Cache.
  - Everyone with an EntityManager reference to the same unit in the same transaction will automatically have a reference to the same PersistenceContext/Cache
  - The PersistenceContext/Cache is flushed and cleared at JTA commit time

Read More: [JPA Concepts: JPA 101.](http://tomee.apache.org/jpa-concepts.html)
