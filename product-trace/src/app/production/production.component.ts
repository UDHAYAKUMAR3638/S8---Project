import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ViewProductionLotsComponent } from '../view-production-lots/view-production-lots.component';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-production',
  templateUrl: './production.component.html',
  styleUrls: ['./production.component.scss']
})
export class ProductionComponent {
  constructor(
    private http: HttpClient,
    public dialog: MatDialog,
  ) { }
  inbounds: any;
  selectedLot: any[] =[];

  ngOnInit() {
    this.http.get("http://localhost:8080/inbound/get").subscribe({
      next: (data) => {
        this.inbounds = data;
        console.log(this.inbounds);
      }
    });
  }

    add(inbound: any, value: number) {
      this.selectedLot.push({ lotNo: inbound.lotNo, quantity: value });
      Swal.fire({
        position: "center",
        icon: "success",
        title: "Material Inserted in Lot",
        showConfirmButton: false,
        timer: 800,
        didOpen: () => {
          const modal = Swal.getPopup();
          modal.style.width = '300px';
          modal.style.height = '220px';
          modal.style.margin = '0px';
          modal.style.padding = '0px';
        },
      });
    
    }

  openDialog(){
   this.dialog.open(ViewProductionLotsComponent,{
    data:this.selectedLot,
    width:"700px",
    height:"500px",
   })
  }

  prevImage(x: any) {
    x.currentImage--;
    console.log(x);
    
  }

  nextImage(x: any) {
    x.currentImage++;
  }

}
