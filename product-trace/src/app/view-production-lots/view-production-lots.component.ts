import { HttpClient } from '@angular/common/http';
import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-view-production-lots',
  templateUrl: './view-production-lots.component.html',
  styleUrls: ['./view-production-lots.component.scss']
})
export class ViewProductionLotsComponent {
  constructor(
    @Inject(MAT_DIALOG_DATA) public details: any, public dialogRef: MatDialogRef<ViewProductionLotsComponent>,
    private http: HttpClient,
    private route: Router
  ) { }

  materialName: string = '';
  lotNo: string = '';
  totalQuantity: number;
  producedDate: Date = new Date();

  save() {
    const obj = { materialName: this.materialName, lotNo: this.lotNo, totalQuantity: this.totalQuantity, producedDate: this.producedDate, inputLots: this.details };
    this.http.post("http://localhost:8080/production", obj).subscribe({
      next: (data) => {
        this.dialogRef.close();
          Swal.fire({
            position: "center",
            icon: "success",
            title: "Inserted to Outbound list",
            showConfirmButton: true,
          });
        
        this.route.navigate(['outbound']);
      }
    })
  }
}
