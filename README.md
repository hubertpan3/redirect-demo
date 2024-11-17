# README

This repo was created to demonstrate a host header attack can be used to redirect a
user to another page, potentially leading to a spoofing attack.

## Getting started

0. Install Java and Git
1. Clone down this repo
2. Compile and run this Spring boot application. e.g. `./mvnw clean spring-boot:run`
3. Verify that you can hit the home page by visiting `http://localhost:8080` in a browser.

## Triggering Host Header Redirect Attack
1. Verify that you can hit the home page by visiting `http://localhost:8080` in a browser.
2. Open up your browser's network inspector for the active tab.
3. Verify that the redirect endpoint works by visiting `http://localhost:8080/redirect` in the same browser tab
3. Verify that have been redirected to `http://localhost:8080/bob`
3. Copy the Request associated with the `302` redirect from your network tab as a curl/powershell command
4. Execute the copied command in shell/powershell to verify that it returns the same response as your browser did.
4. In the command, modify/add the "Host" header so that it points to "www.google.com"
5. Run the modified command and verify that you get Google's 404 not found page.
6. If you did congratulations, you have successfully implemented a Host Header redirect attack.

