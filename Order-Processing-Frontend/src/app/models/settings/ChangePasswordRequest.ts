export class ChangePasswordRequest {
  public currentPassword: string;
  public newPassword: string;

    // empty constructor
    constructor() {
      this.currentPassword = '';
      this.newPassword = '';
    }

}
