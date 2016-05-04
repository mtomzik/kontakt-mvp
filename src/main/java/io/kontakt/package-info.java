/**
 * Domain: we have Category entity. Category can have list of objects assigned.
 * See CategoryService for details.
 *
 * Write an abstract implementation of MVP (Model-View-Presenter) based
 * solution: 1) Main component consisting of two sub components with lists. Lets
 * call them Left and Right list. Every list presents objects assigned to
 * category. 2) Using API from 'framework' package implement Drag And Drop
 * solution of moving element from Left to Right list and vice versa. When
 * element is moved between lists it should be removed from source category and
 * added to a target category. 3) Write tests for the solution, including
 * testing integration between components.
 *
 * Hints: - use CategoryService as backend. - Main component should implement
 * Activity. Let's assume that method 'start' is entry point called by
 * container. - Draggable and DropTarget interfaces are also called by
 * container. You should call them in test manually. - You may write all code as
 * simple POJO's, the goal of task is to program behavior and inter-component
 * communication, solution doesn't need to have visible interface.
 */
package io.kontakt;
