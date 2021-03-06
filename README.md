# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.3.4.RELEASE/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.3.4.RELEASE/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.3.4.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)

### Steps to Run
* You can run the project by just building it and deploying it any server
* This app geenrates unique JIRA-Id's for every project type
* It starts from zero and gets incremented

### Assumptions Made
* Made use of in-memory
* when we go for a real time SQL database, we can use sequence generators which would be unique
* Assumed that 'Project types' can be 'N' numbered
* If we can restrict project types, we can use atomic variables and bump up it's version.
* Currently, relying on synchronous-block to avoid race condition.

### Testing
* Spanned multiple threads using executors and was trying to hit the repo directly
* Results seemed to be satisfying.Below are the results receved.

`Empty..
 res id isDEV-2
 res id isDEV-6
 res id isDEV-1
 res id isDEV-8
 res id isDEV-9
 res id isDEV-5
 res id isDEV-4
 res id isDEV-10
 res id isDEV-7
 res id isDEV-3
 Empty..
 res2 id isQA-1
 res2 id isQA-2
 res2 id isQA-3
 res2 id isQA-4
 res2 id isQA-5
 res2 id isQA-6
 res2 id isQA-7
 res2 id isQA-8
 res2 id isQA-9
`

### API Documentation
* Implemented Swagger
* Also kindly refer https://documenter.getpostman.com/view/3688471/TVYJ6H8c for dowloding the postman collection and the documentation 
