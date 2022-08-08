<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Add new drug</title>
  </head>
  <body>
    <h1>Add new drug</h1>
    <form method="post" action="controller">
      <input name="command" type="hidden" value="create_drug"/>
      <label for="name-input">Name</label><input id="name-input" name="name" type="text"/><br/>
      <label for="releaseForm-input">Release Form</label><input id="releaseForm-input" name="releaseForm" type="text"/><br/>

      <p>Please select the dosage form of the drug:</p>
      <label for="dosageFormChoice1">Tablet</label><input id="dosageFormChoice1" name="dosageForm" type="radio" value="TABLET"/>
      <label for="dosageFormChoice2">Capsule</label><input id="dosageFormChoice2" name="dosageForm" type="radio" value="CAPSULE"/>
      <label for="dosageFormChoice3">Suspension</label><input id="dosageFormChoice3" name="dosageForm" type="radio" value="SUSPENSION"/>
      <label for="dosageFormChoice4">Injection</label><input id="dosageFormChoice4" name="dosageForm" type="radio" value="INJECTION"/><br/>

      <p>Please select the route of administration of the drug:</p>
      <label for="routeAdminChoice1">Orally</label><input id="routeAdminChoice1" name="routeAdministration" type="radio" value="ORALLY"/>
      <label for="routeAdminChoice2">Parenteral</label><input id="routeAdminChoice2" name="routeAdministration" type="radio" value="PARENTERAL"/><br/>

      <p>Need a prescription?:</p>
      <label for="prescriptionChoice1">Yes</label><input id="prescriptionChoice1" name="recipe" type="radio" value="true"/>
      <label for="prescriptionChoice2">No</label><input id="prescriptionChoice2" name="recipe" type="radio" value="false"/><br/>

      <label for="price-input">Price</label><input id="price-input" name="price" type="text"/><br/>
      <label for="quantityInStock-input">Quantity In Stock</label><input id="quantityInStock-input" name="quantityInStock" type="text"/><br/>
      <input type="submit" value="ADD DRUG">
    </form>
    <h3><a href="/drugstore">Main page</a></h3>
  </body>
</html>

