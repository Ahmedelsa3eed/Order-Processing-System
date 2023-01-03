export class Author {
  first_name: string;
  last_name: string;
  address: string;
  phone_number: string;
  author_id: number;

  constructor(){
    this.first_name = "";
    this.last_name="";
    this.address = "";
    this.phone_number = "";
    this.author_id = -1;
  }
}
