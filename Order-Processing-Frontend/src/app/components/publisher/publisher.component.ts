import { Component, OnInit } from '@angular/core';
import { Publisher } from 'src/app/models/Publisher';
import { PublisherService } from 'src/app/services/publisher.service';
import { SignInOutService } from 'src/app/services/sign-in-out.service';
@Component({
  selector: 'app-publisher',
  templateUrl: './publisher.component.html',
  styleUrls: ['./publisher.component.css']
})
export class PublisherComponent implements OnInit {

  constructor(private signInOutService: SignInOutService, private publisherService: PublisherService) { }

  loggedInUserRole = this.signInOutService.getSignedInUserType();
  publishers: Publisher[] = [];
  publisherIdToEdit: number = -1;
  publisherToDeleteName: string = "";
  publisherToEditName: string = "";
  deletePublisherLoading: boolean = false;
  editPublisherLoading: boolean = false;
  addPublisherLoading: boolean = false;
  signedInUserType: string = this.signInOutService.getSignedInUserType();
  pageNum: number = 0;
  pubPerPage = 3;

  ngOnInit(): void {
    console.log(this.signInOutService.getSignedInUserSessionID());
    this.publisherService.getPublisherFromTo(0, this.pubPerPage).subscribe({
      next: (publishers) => {
        console.log(publishers);
        this.publishers = publishers;
      },
      error: (err) => console.log(err), 
    });
  }

  getNextPage() {
    if (this.publishers.length < this.pubPerPage)
      return;
    this.pageNum++;
    this.publisherService.getPublisherFromTo(this.pageNum * this.pubPerPage, (this.pageNum + 1) * this.pubPerPage).subscribe({
      next: (publishers) => {
        this.publishers = publishers;
      },
      error: (err) => alert(err),
    });
  }

  getPreviousPage() {
    if (this.pageNum == 0)
      return;
    this.pageNum--;
    this.publisherService.getPublisherFromTo(this.pageNum * this.pubPerPage, (this.pageNum + 1) * this.pubPerPage).subscribe({
      next: (publishers) => {
        this.publishers = publishers;
      },
      error: (err) => alert(err),
    });
  }

  onAddPublisher (name:string, address:string, phone_number: string): void {
    console.log(name, address, phone_number);
    let p = new Publisher();
    p.address = address;
    p.name = name;
    p.phone_number = phone_number;
    this.publisherService.addPublisher(p).subscribe(
      (res) => {
        console.log(res);
        window.location.reload();
      },
      (err) => {
        alert("Error adding publisher");
      }
    );
  }



  openDeletePublisherModal(publisherName: string) {
    this.publisherToDeleteName = publisherName;
    document.getElementById('openDeletePublisherBtn')?.click();
  }

  openEditPublisherModal(publisherName: string, address:string, phone_number: string, pup_id:number) {
    this.publisherToEditName = publisherName;
    document.getElementById('openEditPublisherBtn')?.click();
    document.getElementById('editPublisherNameInput')?.setAttribute('value', publisherName);
    document.getElementById('editPublisherAddressInput')?.setAttribute('value', address);
    document.getElementById('editPublisherPhoneInput')?.setAttribute('value', phone_number);
    this.publisherIdToEdit = pup_id;
  }

  onDeleteBook() {
    this.deletePublisherLoading = true;
    
  }

  onEditPublisher(name: string, address:string, phone_number:string) {
    this.editPublisherLoading = true;
    let p = new Publisher();
    p.address = address;
    p.name = name;
    p.phone_number = phone_number;
    p.publisher_id = this.publisherIdToEdit
    this.publisherService.editPublisher(p).subscribe({
      next: (res) => {
        console.log(res);
        window.location.reload();
      },
      error: (err) => console.log(err), 
    });
    
  }

  onDeletePublisher(){
    this.deletePublisherLoading = true;
    this.publisherService.deletePublisher(this.publisherToDeleteName).subscribe({
      next: (res) => {
        console.log(res);
        window.location.reload();
      },
      error: (err) => console.log(err), 
    });
  }

}
