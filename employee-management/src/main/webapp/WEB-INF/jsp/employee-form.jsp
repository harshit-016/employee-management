<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Employee Form</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h2>Employee Form</h2>
        <form action="/employees" method="post">

            <input type="hidden" name="id" value="${employee.id}">

            <!-- Name input -->
            <label>Name:</label>
            <input type="text" name="name" class="form-control" value="${employee.name}" required>

            <!-- Email input -->
            <label>Email:</label>
            <input type="email" name="email" class="form-control" value="${employee.email}" required>

            <!-- DOB input -->
            <label>DOB:</label>
            <input type="date" name="dob" class="form-control" value="${employee.dob}" required>

            <!-- Salary input -->
            <label>Salary:</label>
            <input type="number" name="salary" class="form-control" step="0.01" value="${employee.salary}" required>

            <!-- Status checkbox (needs explicit value for checked state) -->
            <label>Status:</label>
            <input type="checkbox" name="status" ${employee.status ? 'checked' : ''}>

            <br>

            <!-- Save Button -->
            <button type="submit" class="btn btn-success">Save</button>
        </form>
    </div>
</body>
</html>
