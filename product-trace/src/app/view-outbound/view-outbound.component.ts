import { HttpClient } from '@angular/common/http';
import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-view-outbound',
  templateUrl: './view-outbound.component.html',
  styleUrls: ['./view-outbound.component.scss']
})
export class ViewOutboundComponent {
  constructor(
    @Inject(MAT_DIALOG_DATA) public details: any, public dialogRef: MatDialogRef<ViewOutboundComponent>,
    private http: HttpClient,
  ) { }

  buyerName: string = '';
  lotNo: string = '';
  invoiceNo: number;
  quantity: number;

  ngOnInit(){
    console.log(this.details);
    
  }

  outbound() {
    const obj = {
      buyerName: this.buyerName,
      lotNo: this.lotNo,
      invoiceNo: this.invoiceNo,
      quantity: this.quantity * 1.0,
      materialComposition: this.details.materialComposition,
      invoiceDate: new Date()
    }

    this.http.post('http://localhost:8080/outbound/save', obj).subscribe({
      next: (data) => {
        console.log("outbound", data);
        this.http.put('http://localhost:8080/production', { id: this.details._id, quantity: this.quantity * 1.0 }).subscribe({
          next: (data) => {
            console.log("update", data);
          }
        });
      },
      complete: () => {
        Swal.fire({
          position: "center",
          icon: "success",
          title: "Material Exported Successfully",
          showConfirmButton: true,
        })
        this.dialogRef.close();
      }
    });
  }
}
