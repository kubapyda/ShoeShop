import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material';

@Component({
  selector: 'app-get-email',
  templateUrl: './get-email.component.html',
  styleUrls: ['./get-email.component.scss']
})
export class GetEmailComponent {

  getEmail: FormGroup;

  constructor(private fb: FormBuilder, private dialog: MatDialogRef<GetEmailComponent>) {
    this.getEmail = this.createGetEmailForm();
  }

  getEmailSubmit(): void {
    this.dialog.close(this.getEmail.value.email);
  }

  private createGetEmailForm(): FormGroup {
    return this.fb.group({
      email: ['', Validators.required]
    });
  }

}
