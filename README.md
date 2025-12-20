# Local Business Support - GUVI Project

This is a servlet-based web application for registering and listing local businesses.
It is designed to match GUVI marking rubric focusing on:
- Servlet implementation
- Code quality & execution
- Innovation (search/filter + file-based storage)

## Structure
```
src/main/java/...  (Java servlets & model & utils)
webapp/            (JSP pages + css)
data/               (file-based storage)
```

## How to run (recommended)
1. Import as a Dynamic Web Project in Eclipse or as a Maven webapp.
2. Ensure Jakarta Servlet API is available (Tomcat 10 / appropriate container).
3. Build and deploy to Tomcat.
4. Open `http://localhost:8080/<app-context>/home` or `/`

## Notes
- Storage is file-based (data/business.txt) to avoid DB setup.
- For production use, replace Storage with a proper database (MySQL/H2).
