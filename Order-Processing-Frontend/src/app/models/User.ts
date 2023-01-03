export class User {
    user_id: number;
    user_name: string;
    first_name: string;
    last_name: string;
    address: string;
    phone_number: string;
    email: string;
    password: string;
    type: string;
    session_id: string;

    constructor() {
        this.user_id = -1;
        this.user_name = '';
        this.first_name = '';
        this.last_name = '';
        this.address = '';
        this.phone_number = '';
        this.email = '';
        this.password = '';
        this.type = '';
        this.session_id = '';
    }
}
