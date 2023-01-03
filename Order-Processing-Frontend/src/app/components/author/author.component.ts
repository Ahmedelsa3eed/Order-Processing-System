import { Component, OnInit } from '@angular/core';
import {SignInOutService} from "../../services/sign-in-out.service";

import {Author} from "../../models/Author";
import {AuthorService} from "../../services/author.service";

@Component({
  selector: 'app-author',
  templateUrl: './author.component.html',
  styleUrls: ['./author.component.css']
})
export class AuthorComponent implements OnInit {

  constructor(private signInOutService: SignInOutService, private authorService: AuthorService) { }

  loggedInUserRole = this.signInOutService.getSignedInUserType();
  authors: Author[] = [];
  authorIdToEdit: number = -1;
  authorToDeleteId: number = 1;
  authorToEditId: number = 1;
  deleteAuthorLoading: boolean = false;
  editAuthorLoading: boolean = false;
  addAuthorLoading: boolean = false;
  signedInUserType: string = this.signInOutService.getSignedInUserType();

  ngOnInit(): void {
    console.log(this.signInOutService.getSignedInUserSessionID());
    this.authorService.getAll().subscribe({
      next: (authors) => {
        console.log(authors);
        this.authors = authors;
      },
      error: (err) => console.log(err),
    });
  }

  onAddAuthor (first_name:string, last_name:string ,address:string, phone_number: string): void {
    console.log(name, address, phone_number);
    let author = new Author();
    author.address = address;
    author.first_name = first_name;
    author.last_name = last_name;
    author.phone_number = phone_number;
    this.authorService.addAuthor(author).subscribe(
      (res) => {
        console.log(res);
        window.location.reload();
      },
      (err) => {
        alert("Error adding Author");
      }
    );
  }



  openDeleteAuthorModal(author_id : number) {
    this.authorToDeleteId = author_id;
    document.getElementById('openDeleteAuthorBtn')?.click();
  }

  openEditAuthorModal(first_name:string, last_name:string, address:string, phone_number: string,author_id:number) {
    this.authorToEditId = author_id;
    document.getElementById('openEditAuthorBtn')?.click();
    document.getElementById('editAuthorFirstNameInput')?.setAttribute('value', first_name);
    document.getElementById('editAuthorLastNameInput')?.setAttribute('value', last_name);
    document.getElementById('editAuthorAddressInput')?.setAttribute('value', address);
    document.getElementById('editAuthorPhoneInput')?.setAttribute('value', phone_number);
    this.authorIdToEdit = author_id;
  }

  onEditAuthor(first_name: string, last_name:string, address:string, phone_number:string) {
    this.editAuthorLoading = true;
    let author = new Author();
    author.address = address;
    author.first_name = first_name;
    author.last_name = last_name;
    author.phone_number = phone_number;
    author.author_id = this.authorIdToEdit;
    this.authorService.editAuthor(author).subscribe({
      next: (res) => {
        console.log(res);
        window.location.reload();
      },
      error: (err) => console.log(err),
    });

  }

  onDeleteAuthor(){
    this.deleteAuthorLoading = true;
    this.authorService.deleteAuthor(this.authorToDeleteId).subscribe({
      next: (res) => {
        console.log(res);
        window.location.reload();
      },
      error: (err) => console.log(err),
    });
  }
}
